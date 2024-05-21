package com.lucienguimaraes.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucienguimaraes.datasource.entities.JokeEntity

@Dao
internal interface JokeDao {

    @Insert
    suspend fun insert(joke: JokeEntity)

    @Query("SELECT * FROM joke_entity WHERE id = :jokeId")
    suspend fun getJokeById(jokeId: Long): JokeEntity?

    @Delete
    suspend fun delete(joke: JokeEntity)
}