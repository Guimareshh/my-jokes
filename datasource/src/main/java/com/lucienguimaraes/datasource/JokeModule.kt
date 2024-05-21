package com.lucienguimaraes.datasource

import com.lucienguimaraes.network.NetworkConnector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object JokeModule {

    @Provides
    fun provideJokeRepository(
        networkConnector: NetworkConnector,
    ): JokeRepository = JokeRepositoryImpl(networkConnector.create(JokeApi::class.java))
}
