package com.androidbox.countries.helpers

import org.mockito.Mockito

class MockitoHelpers {
    companion object {
        /**
         * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
         * null is returned.
         */
        fun <T> any(type : Class<T>): T {
            Mockito.any(type)
            return uninitialized()
        }

        private fun <T> uninitialized(): T = null as T
    }
}