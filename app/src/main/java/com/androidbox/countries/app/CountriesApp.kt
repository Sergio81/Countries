package com.androidbox.countries.app

import android.app.Application
import com.androidbox.countries.component.AppComponent
import com.androidbox.countries.component.DaggerAppComponent

class CountriesApp() : Application() {
    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}