package com.lucienguimaraes.network

import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkConnectorModule {

    @Singleton
    @Provides
    internal fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    internal fun provideHttpClient() = HttpClient()

    @Singleton
    @Provides
    internal fun provideRetrofitClient(
        moshi: Moshi,
        okHttpClient: Lazy<HttpClient>,
        @ApiEndPoint baseUrl: String,
    ) = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .callFactory { okHttpClient.get().getOkHttpClient().newCall(it) }
        .build()

    @Singleton
    @Provides
    internal fun provideNetworkConnector(retrofit: Retrofit): NetworkConnector =
        NetworkConnectorImpl(retrofit)
}