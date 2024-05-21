package com.lucienguimaraes.datasource

import com.lucienguimaraes.datasource.dao.JokeDao
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.network.JokeApi
import com.lucienguimaraes.datasource.network.responses.JokeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class JokeRepositoryImpl @Inject constructor(
    private val jokeDao: JokeDao,
    private val jokeApi: JokeApi,
) : JokeRepository {

    /**
     * Fetch a new joke object from network. We also check if the new joke is already exist in local
     * db to use it instead
     */
    override suspend fun fetchJoke(): Result<JokeEntity> = try {
        val jokeResponse = jokeApi.getJoke(true)

        val jokeEntity = jokeDao
            .getJokeById(jokeResponse.id.toLong())
            ?: JokeEntity(
                id = jokeResponse.id.toLong(),
                content = when (jokeResponse.type) {
                    JokeType.single -> jokeResponse.joke ?: ""
                    JokeType.twopart -> "${jokeResponse.setup}\n\n${jokeResponse.delivery}"
                }
            )

        Result.success(jokeEntity)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    /**
     * Listen to all saved jokes on database, return empty list in case of exception or null
     */
    override fun listenAllJoke(): Flow<List<JokeEntity>> = try {
        jokeDao.listenAllJokeList().filterNotNull()
    } catch (exception: Exception) {
        flowOf(emptyList())
    }

    /**
     * Save the joke in local DB and setting it to favorite by default
     */
    override suspend fun saveJoke(joke: JokeEntity) = try {
        val savedJoke = joke.copy(favorite = true)
        jokeDao.insert(savedJoke)
        Result.success(savedJoke)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    /**
     * Remove the joke by id from local DB
     */
    override suspend fun deleteJoke(jokeId: Long): Result<Unit> = try {
        jokeDao.deleteById(jokeId)
        Result.success(Unit)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    /**
     * Remove the joke from local DB and return the check without being in favorite
     */
    override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> = try {
        jokeDao.delete(joke)
        Result.success(joke.copy(favorite = false))
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

interface JokeRepository {
    fun listenAllJoke(): Flow<List<JokeEntity>>
    suspend fun fetchJoke(): Result<JokeEntity>
    suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity>

    suspend fun deleteJoke(jokeId: Long): Result<Unit>
    suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity>
}
