package com.androidbox.countries.module

import com.androidbox.countries.db.CountryDao
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import androidx.room.Room
import android.app.Application
import com.androidbox.countries.db.CountryDatabase


@Module
class RoomModule(mApplication: Application) {
    private var demoDatabase: CountryDatabase =
        Room.databaseBuilder(mApplication, CountryDatabase::class.java, "country2-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): CountryDatabase {
        return demoDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: CountryDatabase): CountryDao {
        return demoDatabase.countryDao()
    }
}