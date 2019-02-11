package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import retrofit2.Call
import javax.inject.Inject

class CountriesService @Inject constructor(private val countriesAPI: CountriesAPI) {
    fun searchCountry(query:String) : Call<List<Country>> {
        return countriesAPI.searchCountry(query)
    }
}