package com.aagnia.domain.repository

import com.aagnia.common.utils.ResponseWrapper
import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import kotlinx.coroutines.flow.Flow

interface UniversitiesRepository {

    suspend fun universities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>>

}