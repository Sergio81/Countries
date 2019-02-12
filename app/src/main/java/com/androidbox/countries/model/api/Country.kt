package com.androidbox.countries.model.api

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
open class Country {
    @PrimaryKey
    var name: String = ""

    var nativeName: String? = null
    @Ignore
    var callingCodes: List<String>? = null
    var capital: String = ""
    var region: String = ""
    var subregion: String = ""
    @Ignore
    var timezones: List<String>? = null
    @Ignore
    var currencies: List<Currency>? = null
    @Ignore
    var languages: List<Language>? = null
    var flag: String = ""
    var isSaved: Boolean = false
    var inlineCallingCodes: String = ""
        get() {
            return if (field == "")
                callingCodes!!.joinToString("\n")
            else
                field
        }
    var inlineTimeZones: String = ""
        get() {
            return if (field == "")
                timezones!!.joinToString("\n")
            else
                field
        }
    var inlineCurrencies: String = ""
        get() {
            return if (field == "")
                currencies!!.joinToString("\n") { "${it.name} (${it.code})" }
            else
                field
        }
    var inlineLanguages: String = ""
        get() {
            return if (field == "")
                languages!!.joinToString("\n") { "${it.name} (${it.nativeName})" }
            else
                field
        }
}
