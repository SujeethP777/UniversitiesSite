package com.aagnia.data.datasource

import com.aagnia.common.utils.FailureResponseWrapper
import com.aagnia.common.utils.ResponseWrapper
import com.aagnia.common.utils.SuccessResponseWrapper
import com.aagnia.data.network.NetworkClient
import com.aagnia.data.network.NetworkService
import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UniversitiesDataSource {

    suspend fun universities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>>

}

class UniversitiesDataSourceImpl
@Inject
constructor(
    private val networkClient: NetworkClient,
) : UniversitiesDataSource {

    override suspend fun universities(country: String): Flow<ResponseWrapper<List<UniversitiesResponseContainer>>> {
        val api = networkClient.makeService(NetworkService::class.java)
        return flow {
            val responseWrapper =
                networkClient.execute {
                    api.fetchUniversities(country)
                }

            // Handle the success and failure response.
            when (responseWrapper) {
                is SuccessResponseWrapper -> emit(SuccessResponseWrapper(responseWrapper.data))
                is FailureResponseWrapper -> emit(FailureResponseWrapper(responseWrapper.throwable))
                else -> {}
            }
        }
    }
}

