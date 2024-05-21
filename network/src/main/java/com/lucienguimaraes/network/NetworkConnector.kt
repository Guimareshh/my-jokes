package com.lucienguimaraes.network

import retrofit2.Retrofit

internal data class NetworkConnectorImpl(private val client: Retrofit) : NetworkConnector {
    override fun <T> create(service: Class<T>): T = client.create(service)
}

interface NetworkConnector {
    fun <T> create(service: Class<T>): T
}
