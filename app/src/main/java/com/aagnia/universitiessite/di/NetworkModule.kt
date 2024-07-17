package com.aagnia.universitiessite.di

import com.aagnia.data.network.NetworkClient
import com.aagnia.data.network.NetworkServiceConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkService(): NetworkClient {
        return NetworkClient(
            NetworkServiceConfig("http://universities.hipolabs.com/", ""),
        )
    }
}