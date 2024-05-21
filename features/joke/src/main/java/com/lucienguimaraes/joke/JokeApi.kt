package com.lucienguimaraes.joke

import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {

    @GET("joke/Programming/Any")
    suspend fun getJoke(@Query("safe-mode") safeMode: Boolean): JokeResponse
}
