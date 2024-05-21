package com.lucienguimaraes.datasource

import javax.inject.Inject

internal class JokeRepositoryImpl @Inject constructor(private val jokeApi: JokeApi): JokeRepository {

    /**
     * Fetch a new joke object from network and map it to a more concise and readable joke entity
     */
    override suspend fun fetchJoke(): Result<JokeEntity> = try {
        val jokeResponse = jokeApi.getJoke(true)
        val jokeEntity = JokeEntity(
            id = jokeResponse.id.toLong(),
            content = when(jokeResponse.type) {
                JokeType.single -> jokeResponse.joke ?: ""
                JokeType.twopart -> "${jokeResponse.setup}\n\n${jokeResponse.delivery}"
            }
        )
        Result.success(jokeEntity)
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

interface JokeRepository {
    suspend fun fetchJoke(): Result<JokeEntity>
}
