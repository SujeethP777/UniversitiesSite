package com.aagnia.domain.usecase

import com.aagnia.common.utils.ResponseWrapper
import com.aagnia.domain.repository.UniversitiesRepository
import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UniversitiesUseCase {

    suspend fun fetchUniversities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>>
}

class UniversitiesUseCaseImpl
@Inject
constructor(
    private val repository: UniversitiesRepository,
) : UniversitiesUseCase {
    override suspend fun fetchUniversities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>> {
        return repository.universities(country)
    }
}