package com.lucienguimaraes.datasource

import com.lucienguimaraes.datasource.dao.JokeDao
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.network.JokeApi
import com.lucienguimaraes.datasource.network.responses.JokeType
import javax.inject.Inject

internal class JokeRepositoryImpl @Inject constructor(
    private val jokeDao: JokeDao,
    private val jokeApi: JokeApi,
) : JokeRepository {

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
