package com.lucienguimaraes.joke

class JokeRepositoryImpl(private val jokeApi: JokeApi): JokeRepository {

    override suspend fun fetchJoke(): Result<Joke> = try {
        Result.success(jokeApi.getJoke(true))
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

interface JokeRepository {
    suspend fun fetchJoke(): Result<Joke>
}
