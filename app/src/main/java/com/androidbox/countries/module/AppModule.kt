package com.androidbox.countries.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideAppContext(): Context = context
}