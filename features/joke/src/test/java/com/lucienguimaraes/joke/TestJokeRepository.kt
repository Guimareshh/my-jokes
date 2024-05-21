package com.lucienguimaraes.joke

import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.entities.jokeEntityFavorite
import com.lucienguimaraes.datasource.entities.jokeEntityNoFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SuccessJokeRepository : JokeRepository {
    override suspend fun fetchJoke(): Result<JokeEntity> =
        Result.success(jokeEntityNoFavorite())

    override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> =
        Result.success(jokeEntityFavorite())

    override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> =
        Result.success(jokeEntityNoFavorite())

    override fun listenAllJoke(): Flow<List<JokeEntity>> = flow {
        emit(listOf(jokeEntityFavorite()))
    }

    override suspend fun deleteJoke(jokeId: Long): Result<Unit> = Result.success(Unit)
}

class ErrorJokeRepository : JokeRepository {
    override suspend fun fetchJoke(): Result<JokeEntity> = Result.failure(Exception())

    override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> = Result.failure(Exception())

    override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> = Result.failure(Exception())

    override fun listenAllJoke(): Flow<List<JokeEntity>> = flow {
        emit(emptyList())
    }

    override suspend fun deleteJoke(jokeId: Long): Result<Unit> = Result.failure(Exception())
}
