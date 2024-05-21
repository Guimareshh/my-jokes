package com.lucienguimaraes.joke

import com.lucienguimaraes.network.NetworkConnector
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object JokeModule {

    @Provides
    fun provideJokeRepository(
        networkConnector: NetworkConnector,
    ): JokeRepository = JokeRepositoryImpl(networkConnector.create(JokeApi::class.java))
}
