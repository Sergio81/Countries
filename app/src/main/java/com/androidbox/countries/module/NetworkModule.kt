package com.androidbox.countries.module

import com.androidbox.countries.BuildConfig
import com.androidbox.countries.api.CountriesAPI
import com.androidbox.countries.api.CountriesRepository
import com.androidbox.countries.api.CountriesService
import com.androidbox.countries.api.RetrofitInterceptor
import com.androidbox.countries.db.CountryDao
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(RetrofitInterceptor()).build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, factory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRepository(countriesService: CountriesService, countryDao: CountryDao) : CountriesRepository{
        return CountriesRepository(countriesService, countryDao)
    }
}
