package com.androidbox.countries.api

import org.junit.Test
import retrofit2.mock.MockRetrofit
import retrofit2.Retrofit
import org.junit.Before
import io.reactivex.subscribers.TestSubscriber
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.mock.NetworkBehavior

class CountriesAPIUnitTest {
    private val behavior = NetworkBehavior.create()
    private val testSubscriber = TestSubscriber.create<Any>()
    private lateinit var mockService: CountriesAPI

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl("http://example.com").build()

        val mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior).build()

        val delegate = mockRetrofit.create<CountriesAPI>(CountriesAPI::class.java)

        mockService = CountriesServiceMock(delegate)
    }

    @Test
    fun getUSACountry() {
        //givenNetworkFailurePercentIs(0)

        mockService.searchCountry("Mexico")

        testSubscriber.assertValue("test")
        //testSubscriber.assertCompleted()
    }
}