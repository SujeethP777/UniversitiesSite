package com.aagnia.data.repository

import com.aagnia.common.utils.ResponseWrapper
import com.aagnia.data.datasource.UniversitiesDataSource
import com.aagnia.domain.repository.UniversitiesRepository
import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversitiesRepositoryImpl
@Inject
constructor(
    private val datasource: UniversitiesDataSource,
) : UniversitiesRepository {

    override suspend fun universities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>> =
        datasource.universities(country)

}
