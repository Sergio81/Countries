package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CountriesService @Inject constructor(private val countriesAPI: CountriesAPI) {
    fun searchCountry(query:String) : Call<List<Country>> {
        return countriesAPI.searchCountry(query)
    }
}