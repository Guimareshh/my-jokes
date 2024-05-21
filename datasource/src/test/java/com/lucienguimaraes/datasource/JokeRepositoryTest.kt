package com.lucienguimaraes.datasource

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class JokeRepositoryTest {

    @Test
    fun `Given a success fetch of single part joke it should return a joke entity `() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(TestJokeApi())

            assertEquals(
                Result.success(
                    JokeEntity(
                        id = 23,
                        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                    )
                ),
                repository.fetchJoke()
            )
        }
    }

    @Test
    fun `Given a success fetch of two part joke it should return a joke entity `() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseTwoPart()
            }

            val repository: JokeRepository = JokeRepositoryImpl(TestJokeApi())

            assertEquals(
                Result.success(
                    JokeEntity(
                        id = 5,
                        content = "Why did the web developer walk out of a restaurant in disgust?\n\nThe seating was laid out in tables.",
                    )
                ),
                repository.fetchJoke()
            )
        }
    }

    @Test
    fun `Given a fail to fetch a joke it should return an error`() {
        runBlocking {

            val exception = Exception()

            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = throw exception
            }

            val repository: JokeRepository = JokeRepositoryImpl(TestJokeApi())

            assertEquals(
                Result.failure<JokeEntity>(exception),
                repository.fetchJoke()
            )
        }
    }
}