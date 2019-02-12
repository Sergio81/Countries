package com.androidbox.countries.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidbox.countries.db.CountryDao
import com.androidbox.countries.model.api.Country
import io.reactivex.Completable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Callback
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Singleton

@Singleton
open class CountriesRepository @Inject constructor(
    private val countriesService: CountriesService,
    private val countryDao: CountryDao
) {
    val countries = MutableLiveData<List<Country>>()

    open fun getCountriesList(query: String) {
        if (query != "") {
            countriesService.searchCountry(query).enqueue(object : Callback<List<Country>> {
                override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                    countries.value = response.body()
                }

                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    Log.d("CountriesRepository", t.message)
                }
            })
        } else {
            doAsync {
                val d: List<Country> = countryDao.getAllCountries()
                if (d.isNotEmpty())
                    Log.d("CountriesRepository", d.size.toString())
                uiThread {
                    countries.value = d
                }
            }
        }
    }

    open fun getCountry(index: Int) = countries.value!![index]

    open fun saveCountry(item: Country) = Completable.fromAction { countryDao.insertCountry(item) }!!
}