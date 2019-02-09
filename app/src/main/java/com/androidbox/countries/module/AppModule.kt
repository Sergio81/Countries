package com.androidbox.countries.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application):
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}