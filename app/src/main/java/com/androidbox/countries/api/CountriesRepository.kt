package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import io.reactivex.Observable

class CountriesRepository(val countriesService: CountriesService){

    fun getProjectList(query: String): Observable<List<Country>> {
        return countriesService.searchCountry(query)
    }
}