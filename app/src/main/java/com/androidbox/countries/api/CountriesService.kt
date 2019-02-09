package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import io.reactivex.Observable
import javax.inject.Inject

class CountriesService @Inject constructor(val countriesAPI: CountriesAPI) {
    fun searchCountry(query:String) : Observable<List<Country>> {
        return countriesAPI.searchCountry(query)
    }
}