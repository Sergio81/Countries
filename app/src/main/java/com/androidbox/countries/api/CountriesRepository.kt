package com.androidbox.countries.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.androidbox.countries.model.api.Country


class CountriesRepository(val countriesService: CountriesService){

    fun getProjectList(query: String): LiveData<List<Country>> {
        val data = MutableLiveData<List<Country>>()

        countriesService.searchCountry(query)
            .subscribe()
//        countriesService!!.getProjectList(userId).enqueue(object : Callback<List<Country>>() {
//            fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
//                data.setValue(response.body())
//            }
//
//            // Error handling will be explained in the next article …
//        })

        return data
    }
}