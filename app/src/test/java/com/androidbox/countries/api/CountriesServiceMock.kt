package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate

class CountriesServiceMock(private val  delegate: BehaviorDelegate<CountriesAPI>) :CountriesAPI{
    private val countryName = "Mexico"
    private val country = Country().apply {
        name = countryName
    }

    override fun searchCountry(query: String): Call<List<Country>> {
        val countries = ArrayList<Country>()
        for (i in (0..9))
            countries.add(country)

        return delegate.returningResponse(countries).searchCountry("")
    }

}