package com.androidbox.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidbox.countries.api.CountriesRepository
import com.androidbox.countries.model.api.Country
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val countriesRepository: CountriesRepository) : ViewModel() {
    var status =  MutableLiveData<String>()

    fun countries()
        = CountriesRepository.countries

    fun searchCountry(query:String)
            = countriesRepository.getCountriesList(query)

    fun getCountry(index:Int)
            = countriesRepository.getCountry(index)

    fun saveCountry(item:Country){
        doAsync {
            countriesRepository.saveCountry(item)
            uiThread {
                status.value = "Country Saved"
            }
        }
    }
}
