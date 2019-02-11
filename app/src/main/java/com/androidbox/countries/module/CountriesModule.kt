package com.androidbox.countries.module

import com.androidbox.countries.api.CountriesAPI
import com.androidbox.countries.api.CountriesService
import com.androidbox.countries.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CountriesModule {
    @Provides
    @Singleton
    internal fun provideRetrofit(retrofit: Retrofit): CountriesAPI {
        return retrofit.create<CountriesAPI>(CountriesAPI::class.java)
    }

    @Provides
    @Singleton
    internal fun provideCountriesService(countriesAPI: CountriesAPI) : CountriesService{
        return CountriesService(countriesAPI)
    }
}