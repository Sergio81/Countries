package com.androidbox.countries.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidbox.countries.db.CountryDao
import com.androidbox.countries.model.api.Country
import retrofit2.Callback
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
    private val countriesService: CountriesService,
    private val countryDao: CountryDao
){

    companion object {
        private val data by lazy { MutableLiveData<List<Country>>() }
    }

    fun getCountriesList(query: String): LiveData<List<Country>> {

        countriesService.searchCountry(query).enqueue(object : Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.d("CountriesRepository", t.message)
            }
        })

        return data
    }

    fun getCountry(index:Int) = data.value!![index]

    fun getOfflineCountry(name:String) : LiveData<Country>{
        return countryDao.getCountry(name)
    }

    fun getAllOflineCountries() : LiveData<List<Country>>{
        return countryDao.getAllCountries()
    }
}