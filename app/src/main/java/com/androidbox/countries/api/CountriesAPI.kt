package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {
    @GET("name/{name}")
    fun searchCountry(@Path("name")query:String): Call<List<Country>>

//    companion object {
//        fun create():CountriesAPI{
//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BuildConfig.END_POINT)
//                .build()
//
//            return retrofit.create(CountriesAPI::class.java)
//        }
//    }
}