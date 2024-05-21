package com.lucienguimaraes.joke

import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(): JokeRepository {

    override suspend fun fetchJoke(): Result<Joke> = try {
        Result.success(jokeMock())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

interface JokeRepository {
    suspend fun fetchJoke(): Result<Joke>
}
