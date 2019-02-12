package com.androidbox.countries.api

import androidx.annotation.NonNull
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.androidbox.countries.db.CountryDao
import com.androidbox.countries.model.api.Country
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Test
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.junit.BeforeClass
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@Suppress("UNCHECKED_CAST")
class CountriesRepositoryTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()!!
    @Mock
    lateinit var countriesService: CountriesService
    @Mock
    lateinit var countryDao: CountryDao
    @Mock
    lateinit var observer: Observer<List<Country>>
    @InjectMocks
    lateinit var countriesRepository: CountriesRepository

    private val countryName = "Mexico"
    private val country = Country().apply {
        name = countryName
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }

            ArchTaskExecutor.getInstance()
                .setDelegate(object : TaskExecutor() {
                    override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                    override fun postToMainThread(runnable: Runnable) = runnable.run()

                    override fun isMainThread(): Boolean = true
                })
        }
    }

    @Test
    fun getDataFromDatabase() {
        countriesRepository.countries.observeForever(observer)

        val countries = ArrayList<Country>()
        for (i in (0..9))
            countries.add(country)

        Mockito.`when`(countryDao.getCountry("")).then {
            var response = MutableLiveData<List<Country>>()

            response.value = countries
            response
        }

        countriesRepository.getCountriesList("")

        verify(observer).onChanged(countriesRepository.countries.value)
        Assert.assertEquals(10, countriesRepository.countries.value!!.size)
    }

    @Test
    fun getDataFromRestAPI() {
        //val mockedCall = Mockito.mock<Call<List<Country>>>(Call<List<Country>>::class)
        countriesRepository.countries.observeForever(observer)
        val countries = ArrayList<Country>()
        for (i in (0..9))
            countries.add(country)

        val response = Response.success(countries)

        `when`(countriesService.searchCountry(ArgumentMatchers.anyString()))
            .thenAnswer {
                val callback = it.arguments[1] as Callback<List<Country>>
                callback
                null
            }

        countriesRepository.getCountriesList(countryName)

        verify(observer).onChanged(countriesRepository.countries.value)
    }
}