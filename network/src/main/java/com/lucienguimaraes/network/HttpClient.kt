package com.lucienguimaraes.network

import okhttp3.OkHttpClient

/**
 * http interceptor or logging can be added here to further customise needs
 */
class HttpClient {
    fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}
