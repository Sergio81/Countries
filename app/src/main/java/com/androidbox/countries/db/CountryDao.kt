package com.androidbox.countries.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.androidbox.countries.model.api.Country
import dagger.Module

@Module
@Dao
interface CountryDao {
    @Insert(onConflict = REPLACE)
    fun save(country: Country)

    @Query("SELECT * FROM country WHERE name = :countryName")
    fun getCountry(countryName: String): LiveData<Country>

    @Query("SELECT * FROM country")
    fun getAllCountries() : List<Country>

    @Insert
    fun insertCountry(vararg country: Country)
}