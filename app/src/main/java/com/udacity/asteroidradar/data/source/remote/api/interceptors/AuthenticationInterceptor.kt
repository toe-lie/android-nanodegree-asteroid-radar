package com.udacity.asteroidradar.data.source.remote.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url =
            originalHttpUrl
                .newBuilder()
                .addQueryParameter("api_key", "MWgdh6GtcZBOR4R1cOEd1SNsuzYDuQ6zjNFfk7cz")
                .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}