package com.androidbox.countries.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translations {

    @SerializedName("de")
    @Expose
    var de: String? = null
    @SerializedName("es")
    @Expose
    var es: String? = null
    @SerializedName("fr")
    @Expose
    var fr: String? = null
    @SerializedName("ja")
    @Expose
    var ja: String? = null
    @SerializedName("it")
    @Expose
    var it: String? = null
    @SerializedName("br")
    @Expose
    var br: String? = null
    @SerializedName("pt")
    @Expose
    var pt: String? = null
    @SerializedName("nl")
    @Expose
    var nl: String? = null
    @SerializedName("hr")
    @Expose
    var hr: String? = null
    @SerializedName("fa")
    @Expose
    var fa: String? = null
}
