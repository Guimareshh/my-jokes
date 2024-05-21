package com.lucienguimaraes.datasource

import app.cash.turbine.test
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.network.JokeApi
import com.lucienguimaraes.datasource.network.responses.JokeResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class JokeRepositoryTest {

    //region fetch joke
    @Test
    fun `Given a success fetch of single part joke it should return a joke entity `() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(EmptyTestJokeDao(), TestJokeApi())

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
    fun `Given a success fetch of single part joke with existing joke it should return a joke entity `() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(SuccessTestJokeDao(), TestJokeApi())

            assertEquals(
                Result.success(
                    JokeEntity(
                        id = 23,
                        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                        favorite = true,
                    )
                ),
                repository.fetchJoke()
            )
        }
    }

    @Test
    fun `Given a success fetch of single part joke with fail to check local joke it should return an error`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(ErrorTestJokeDao(), TestJokeApi())

            assertEquals(true, repository.fetchJoke().isFailure)
        }
    }

    @Test
    fun `Given a success fetch of two part joke it should return a joke entity `() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseTwoPart()
            }

            val repository: JokeRepository = JokeRepositoryImpl(EmptyTestJokeDao(), TestJokeApi())

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

            val repository: JokeRepository = JokeRepositoryImpl(EmptyTestJokeDao(), TestJokeApi())

            assertEquals(
                Result.failure<JokeEntity>(exception),
                repository.fetchJoke()
            )
        }
    }

    @Test
    fun `Given a success listen jokes it should return a flow of list of joke`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseTwoPart()
            }

            val repository: JokeRepository = JokeRepositoryImpl(SuccessTestJokeDao(), TestJokeApi())

            repository.listenAllJoke().test {
                assertEquals(
                    listOf(
                        JokeEntity(
                            id = 23,
                            content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                            favorite = true,
                        )
                    ), awaitItem()
                )
                awaitComplete()
            }
        }
    }

    @Test
    fun `Given a null on listen jokes it should complete`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseTwoPart()
            }

            val repository: JokeRepository = JokeRepositoryImpl(EmptyTestJokeDao(), TestJokeApi())

            repository.listenAllJoke().test {
                awaitComplete()
            }
        }
    }

    @Test
    fun `Given a fail to listen jokes it should return a flow of empty list of joke`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseTwoPart()
            }

            val repository: JokeRepository = JokeRepositoryImpl(ErrorTestJokeDao(), TestJokeApi())

            repository.listenAllJoke().test {
                assertEquals(emptyList<JokeEntity>(), awaitItem())
                awaitComplete()
            }
        }
    }
    //endregion

    //region save joke
    @Test
    fun `Given a success save joke it should return the joke as favorite`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(SuccessTestJokeDao(), TestJokeApi())

            val joke = JokeEntity(
                id = 23,
                content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                favorite = false,
            )

            assertEquals(
                Result.success(
                    JokeEntity(
                        id = 23,
                        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                        favorite = true,
                    )
                ),
                repository.saveJoke(joke)
            )
        }
    }

    @Test
    fun `Given a fail to save joke it should return an error`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(ErrorTestJokeDao(), TestJokeApi())

            val joke = JokeEntity(
                id = 23,
                content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                favorite = false,
            )

            assertEquals(true, repository.saveJoke(joke).isFailure)
        }
    }
    //endregion

    //region delete joke
    @Test
    fun `Given a success delete joke it should return the joke not as favorite`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(SuccessTestJokeDao(), TestJokeApi())

            val joke = JokeEntity(
                id = 23,
                content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                favorite = true,
            )

            assertEquals(
                Result.success(
                    JokeEntity(
                        id = 23,
                        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                        favorite = false,
                    )
                ),
                repository.deleteJoke(joke)
            )
        }
    }

    @Test
    fun `Given a fail to delete joke it should return an error`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(ErrorTestJokeDao(), TestJokeApi())

            val joke = JokeEntity(
                id = 23,
                content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
                favorite = true,
            )

            assertEquals(true, repository.deleteJoke(joke).isFailure)
        }
    }

    @Test
    fun `Given a success delete joke by id it should return void`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(SuccessTestJokeDao(), TestJokeApi())

            assertEquals(Result.success(Unit), repository.deleteJoke(23))
        }
    }

    @Test
    fun `Given a fail to delete joke by id it should return an error`() {
        runBlocking {
            class TestJokeApi : JokeApi {
                override suspend fun getJoke(safeMode: Boolean): JokeResponse = jokeResponseSingle()
            }

            val repository: JokeRepository = JokeRepositoryImpl(ErrorTestJokeDao(), TestJokeApi())

            assertEquals(true, repository.deleteJoke(23).isFailure)
        }
    }
    //endregion
}