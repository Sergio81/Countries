package com.androidbox.countries.model.api

data class Country(
    var name: String? = null,
    var nativeName: String? = null,
    var callingCodes: List<String>? = null,
    var capital: String? = null,
    var region: String? = null,
    var subregion: String? = null,
    var timezones: List<String>? = null,
    var currencies: List<Currency>? = null,
    var languages: List<Language>? = null,
    var flag: String? = null,
    var isSaved: Boolean = false
) {
    var inlineCallingCodes: String = ""
        private set
        get() {
            return callingCodes!!.joinToString("\n")
        }
    var inlineTimeZones: String = ""
        private set
        get() {
            return timezones!!.joinToString("\n")
        }
    var inlineCurrencies: String = ""
        private set
        get() {
            return currencies!!.joinToString("\n") { "${it.name} (${it.code})" }
        }
    var inlineLanguages: String = ""
        private set
        get() {
            return languages!!.joinToString("\n") { "${it.name} (${it.nativeName})" }
        }
}
