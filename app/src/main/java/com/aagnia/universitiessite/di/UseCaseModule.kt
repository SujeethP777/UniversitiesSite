package com.aagnia.universitiessite.di

import com.aagnia.domain.repository.UniversitiesRepository
import com.aagnia.domain.usecase.UniversitiesUseCase
import com.aagnia.domain.usecase.UniversitiesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUniversitiesUseCase(repository: UniversitiesRepository): UniversitiesUseCase = UniversitiesUseCaseImpl(repository)
}