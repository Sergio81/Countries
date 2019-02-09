package com.androidbox.countries.component

import android.content.Context
import com.androidbox.countries.api.CountriesAPI
import com.androidbox.countries.api.RetrofitInterceptor
import com.androidbox.countries.module.AppModule
import com.androidbox.countries.module.CountriesModule
import com.androidbox.countries.module.NetworkModule
import com.androidbox.countries.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, CountriesModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(interceptor: RetrofitInterceptor)
}