package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {
    @GET("name/{name}")
    fun searchCountry(@Path("name")query:String): Observable<List<Country>>
}