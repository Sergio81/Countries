package com.androidbox.countries.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country2(
    @PrimaryKey
    var name: String,
    var nativeName: String,
    var capital: String,
    var region: String,
    var subregion: String,
    var flag: String,
    var isSaved: Boolean,
    var inlineCallingCodes: String,
    var inlineTimeZones: String,
    var inlineCurrencies: String,
    var inlineLanguages: String
)
