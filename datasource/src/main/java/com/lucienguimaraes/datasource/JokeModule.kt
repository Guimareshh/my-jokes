package com.lucienguimaraes.datasource

import android.content.Context
import androidx.room.Room
import com.lucienguimaraes.datasource.network.JokeApi
import com.lucienguimaraes.network.NetworkConnector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object JokeModule {

    @Provides
    internal fun provideDataBase(@ApplicationContext appContext: Context) = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "app-database")
        .build()

    @Provides
    fun provideJokeRepository(
        appDatabase: AppDatabase,
        networkConnector: NetworkConnector,
    ): JokeRepository = JokeRepositoryImpl(
        appDatabase.jokeDao(),
        networkConnector.create(JokeApi::class.java),
        )
}
