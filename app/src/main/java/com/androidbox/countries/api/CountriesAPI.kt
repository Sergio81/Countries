package com.androidbox.countries.api

import androidx.lifecycle.LiveData
import com.androidbox.countries.BuildConfig
import com.androidbox.countries.model.api.Country
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {
    @GET("name/{name}")
    fun searchCountry(@Path("name")query:String):Observable<List<Country>>

    companion object {
        fun create():CountriesAPI{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.END_POINT)
                .build()

            return retrofit.create(CountriesAPI::class.java)
        }
    }
}