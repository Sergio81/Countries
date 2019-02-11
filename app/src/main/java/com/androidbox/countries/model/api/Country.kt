package com.androidbox.countries.model.api

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Country
 {
     @PrimaryKey
     var name: String = ""

     var nativeName: String? = null
     @Ignore
     var callingCodes: List<String>? = null
     var capital: String? = null
     var region: String? = null
     var subregion: String? = null
     @Ignore
     var timezones: List<String>? = null
     @Ignore
     var currencies: List<Currency>? = null
     @Ignore
     var languages: List<Language>? = null
     var flag: String? = null
     var isSaved: Boolean = false
    var inlineCallingCodes: String = ""
        get() {
            return callingCodes!!.joinToString("\n")
        }
    var inlineTimeZones: String = ""
        get() {
            return timezones!!.joinToString("\n")
        }
    var inlineCurrencies: String = ""
        get() {
            return currencies!!.joinToString("\n") { "${it.name} (${it.code})" }
        }
    var inlineLanguages: String = ""
        get() {
            return languages!!.joinToString("\n") { "${it.name} (${it.nativeName})" }
        }
}
