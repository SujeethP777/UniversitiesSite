package com.aagnia.universitiessite.di

import com.aagnia.data.datasource.UniversitiesDataSource
import com.aagnia.data.datasource.UniversitiesDataSourceImpl
import com.aagnia.data.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideUniversitiesDataSource(networkService: NetworkClient): UniversitiesDataSource =
        UniversitiesDataSourceImpl(networkService)
}