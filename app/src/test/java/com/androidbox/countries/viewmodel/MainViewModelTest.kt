package com.androidbox.countries.viewmodel

import com.androidbox.countries.api.CountriesRepository
import com.androidbox.countries.helpers.MockitoHelpers.Companion.any
import com.androidbox.countries.model.api.Country
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import io.reactivex.Completable
import junit.framework.Assert.assertEquals
import org.mockito.ArgumentMatchers.anyInt

class MainViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()!!
    @Mock
    lateinit var countriesRepository : CountriesRepository
    @InjectMocks
    lateinit var mainViewModel : MainViewModel

    private val countryName = "test Name Country"
    private val country = Country().apply {
        name = countryName
    }

    @Test
    fun getCountry() {
        Mockito.`when`(countriesRepository.getCountry(anyInt())).thenReturn(country)

        val response = mainViewModel.getCountry(0)

        assertEquals(countryName, response.name)
    }

    @Test
    fun saveCountry() {
        val testObserver = TestObserver<String>()

        Mockito.`when`(countriesRepository.saveCountry(any(Country::class.java))).thenReturn(Completable.complete())

        mainViewModel.saveCountry(country).subscribe(testObserver)

        testObserver.assertTerminated()
        testObserver.assertNoErrors()
    }
}