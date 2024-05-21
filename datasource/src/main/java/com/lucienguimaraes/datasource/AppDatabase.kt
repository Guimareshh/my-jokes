package com.lucienguimaraes.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucienguimaraes.datasource.dao.JokeDao
import com.lucienguimaraes.datasource.entities.JokeEntity

@Database(
    version = 1,
    entities = [JokeEntity::class],
)

internal abstract class AppDatabase : RoomDatabase() {
    internal abstract fun jokeDao(): JokeDao
}