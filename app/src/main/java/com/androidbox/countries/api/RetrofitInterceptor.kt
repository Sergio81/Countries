package com.androidbox.countries.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit


/**
 * Interceptor for Retrofit to add cache to header
 */
class RetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(2, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}