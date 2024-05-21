package com.lucienguimaraes.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * http interceptor or logging can be added here to further customise needs
 */
class HttpClient @Inject constructor(private val logging: Boolean) {

    fun getOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        if (logging) {
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return httpClient.build()
    }
}
