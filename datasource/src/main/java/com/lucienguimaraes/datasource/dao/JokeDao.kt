package com.lucienguimaraes.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucienguimaraes.datasource.entities.JokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface JokeDao {

    @Insert
    suspend fun insert(joke: JokeEntity)

    @Query("SELECT * FROM joke_entity WHERE id = :jokeId")
    suspend fun getJokeById(jokeId: Long): JokeEntity?

    @Query("SELECT * FROM joke_entity")
    fun listenAllJokeList(): Flow<List<JokeEntity>?>

    @Delete
    suspend fun delete(joke: JokeEntity)

    @Query("DELETE FROM joke_entity WHERE id = :jokeId")
    suspend fun deleteById(jokeId: Long)
}
