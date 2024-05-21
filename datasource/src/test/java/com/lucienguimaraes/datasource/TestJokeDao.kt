package com.lucienguimaraes.datasource

import com.lucienguimaraes.datasource.dao.JokeDao
import com.lucienguimaraes.datasource.entities.JokeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ErrorTestJokeDao: JokeDao {

    override suspend fun insert(joke: JokeEntity) = throw Exception()

    override suspend fun getJokeById(jokeId: Long): JokeEntity = throw Exception()

    override suspend fun delete(joke: JokeEntity) = throw Exception()

    override fun listenAllJokeList(): Flow<List<JokeEntity>?> = throw Exception()

    override suspend fun deleteById(jokeId: Long) = throw Exception()
}

class SuccessTestJokeDao: JokeDao {

    private val joke = JokeEntity(
        id = 23,
        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
        favorite = true,
    )

    override suspend fun insert(joke: JokeEntity) = Unit

    override suspend fun getJokeById(jokeId: Long): JokeEntity = joke

    override suspend fun delete(joke: JokeEntity) = Unit

    override fun listenAllJokeList(): Flow<List<JokeEntity>?> = flow {
        emit(listOf(joke))
    }

    override suspend fun deleteById(jokeId: Long) = Unit
}

class EmptyTestJokeDao: JokeDao {

    override suspend fun insert(joke: JokeEntity) = Unit

    override suspend fun getJokeById(jokeId: Long): JokeEntity? = null

    override suspend fun delete(joke: JokeEntity) = Unit

    override fun listenAllJokeList(): Flow<List<JokeEntity>?> = flow {
        emit(null)
    }

    override suspend fun deleteById(jokeId: Long) = Unit
}
