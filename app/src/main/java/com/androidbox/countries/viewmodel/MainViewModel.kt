package com.androidbox.countries.viewmodel

import androidx.lifecycle.ViewModel
import com.androidbox.countries.api.CountriesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val countriesRepository: CountriesRepository) : ViewModel() {
    fun searchCountry(query:String)
            = countriesRepository.getCountriesList(query)

    fun getCountry(index:Int) = countriesRepository.getCountry(index)
}
