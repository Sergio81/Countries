package com.androidbox.countries.api

import androidx.lifecycle.MutableLiveData
import com.androidbox.countries.db.CountryDao
import com.androidbox.countries.model.api.Country
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CountriesRepository @Inject constructor(
    private val countriesService: CountriesService,
    private val countryDao: CountryDao
) {
    val countries = MutableLiveData<List<Country>>()

    open fun getCountriesList(query: String) {
        if (query != "") {
            var tmp = countriesService.searchCountry(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    countries.value = it
                }
        } else {
            var tmp = countryDao.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    countries.value = it
            }
        }
    }

    open fun getCountry(index: Int) = countries.value!![index]

    open fun saveCountry(item: Country) = Completable.fromAction { countryDao.insertCountry(item) }!!
}