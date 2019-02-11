package com.androidbox.countries.db

import androidx.room.RoomDatabase
import androidx.room.Database
import com.androidbox.countries.model.api.Country


@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDao
}
