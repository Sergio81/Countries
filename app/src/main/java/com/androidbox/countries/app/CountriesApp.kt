package com.androidbox.countries.app

import android.app.Application
import com.androidbox.countries.component.AppComponent
import com.androidbox.countries.component.DaggerAppComponent
import com.androidbox.countries.module.RoomModule

open class CountriesApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .build()
    }
}