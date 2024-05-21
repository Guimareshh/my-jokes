package com.lucienguimaraes.network

import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkConnectorModule {

    private const val BASE_URL ="https://v2.jokeapi.dev"
    private const val LOGGING = true

    @Provides
    internal fun provideMoshi() = Moshi.Builder().build()

    @Provides
    internal fun provideHttpClient() = HttpClient(LOGGING)

    @Provides
    internal fun provideRetrofitClient(moshi: Moshi, okHttpClient: Lazy<HttpClient>) = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .callFactory { okHttpClient.get().getOkHttpClient().newCall(it) }
        .build()

    @Provides
    internal fun provideNetworkConnector(retrofit: Retrofit): NetworkConnector =
        NetworkConnectorImpl(retrofit)
}
