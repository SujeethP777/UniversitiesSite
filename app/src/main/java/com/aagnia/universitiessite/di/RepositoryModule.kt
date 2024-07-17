package com.aagnia.universitiessite.di

import com.aagnia.data.datasource.UniversitiesDataSource
import com.aagnia.data.repository.UniversitiesRepositoryImpl
import com.aagnia.domain.repository.UniversitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUniversitiesRepository(datasource: UniversitiesDataSource): UniversitiesRepository =
        UniversitiesRepositoryImpl(datasource)
}