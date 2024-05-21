package com.lucienguimaraes.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import com.lucienguimaraes.datasource.entities.JokeEntity

@Dao
internal interface JokeDao {

    @Insert
    fun insertAll(vararg joke: JokeEntity)
}