package com.androidbox.countries.module

import com.androidbox.countries.api.CountriesAPI
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
}