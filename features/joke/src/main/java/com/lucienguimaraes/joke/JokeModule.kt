package com.lucienguimaraes.joke

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JokeModule {

    @Binds
    internal abstract fun bindsJokeRepository(jokeRepository: JokeRepositoryImpl): JokeRepository
}
