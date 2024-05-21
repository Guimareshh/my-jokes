package com.lucienguimaraes.joke

import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.entities.jokeEntityFavorite
import com.lucienguimaraes.datasource.entities.jokeEntityNoFavorite

class SuccessJokeRepository : JokeRepository {
    override suspend fun fetchJoke(): Result<JokeEntity> =
        Result.success(jokeEntityNoFavorite())

    override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> =
        Result.success(jokeEntityFavorite())

    override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> =
        Result.success(jokeEntityNoFavorite())
}

class ErrorJokeRepository : JokeRepository {
    override suspend fun fetchJoke(): Result<JokeEntity> = Result.failure(Exception())

    override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> = Result.failure(Exception())

    override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> = Result.failure(Exception())
}
