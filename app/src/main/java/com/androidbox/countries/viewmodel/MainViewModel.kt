package com.androidbox.countries.viewmodel

import androidx.lifecycle.ViewModel
import com.androidbox.countries.api.CountriesRepository
import com.androidbox.countries.model.api.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val countriesRepository: CountriesRepository) : ViewModel() {
    /***
     * Get the list with the latest search
     */
    fun countries() = countriesRepository.countries

    /***
     * Start async search, if the query is empty,
     * it will search in database
     */
    fun searchCountry(query: String) = countriesRepository.getCountriesList(query)

    /***
     * get the country details from the latest search
     */
    fun getCountry(index: Int) = countriesRepository.getCountry(index)

    /***
     * Save country to data base
     */
    fun saveCountry(item: Country)
        = countriesRepository.saveCountry(item)
}
