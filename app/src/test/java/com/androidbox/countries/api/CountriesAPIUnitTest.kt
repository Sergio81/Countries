package com.androidbox.countries.api

import com.androidbox.countries.model.api.Country
import io.reactivex.observers.TestObserver
import org.junit.Test

class CountriesAPIUnitTest {
    @Test
    fun getUSACountry() {
        val testObserver = TestObserver<List<Country>>()

        val countriesAPI by lazy {
            CountriesAPI.create()
        }

        countriesAPI.SearchCountry("United States of America").subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

    }
}