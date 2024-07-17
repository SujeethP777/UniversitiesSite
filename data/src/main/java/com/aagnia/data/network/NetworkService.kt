package com.aagnia.data.network

import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("search")
    suspend fun fetchUniversities(
        @Query("country") country: String
    ): Response<List<UniversitiesResponseContainer>>
}