package com.lucienguimaraes.joke

import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(private val jokeApi: JokeApi): JokeRepository {

    override suspend fun fetchJoke(): Result<JokeResponse> = try {
        Result.success(jokeApi.getJoke(true))
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

interface JokeRepository {
    suspend fun fetchJoke(): Result<JokeResponse>
}
