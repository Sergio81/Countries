package com.androidbox.countries.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidbox.countries.api.CountriesAPI
import com.androidbox.countries.model.api.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class MainViewModel : ViewModel() {
    val countriesLiveData = MutableLiveData<List<Country>>()
    var selectedCountry = MutableLiveData<Country>()

    private var disposable:Disposable? = null

    fun searchCountry(query:String){
        val countriesAPI by lazy {
            CountriesAPI.create()
        }

        disposable = countriesAPI.searchCountry(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (this::onDataReceived, this::onError)
    }

    fun selectCountry(index:Int){
        selectedCountry.value = countriesLiveData.value!![index]
    }

    private fun onDataReceived(countriesList: List<Country>){
        countriesLiveData.value = countriesList
        disposable?.dispose()
    }

    private fun onError(error: Throwable) {
        countriesLiveData.value = emptyList()
        disposable?.dispose()
        Log.d("MAIN_ACTIVITY", error.message)
    }
}