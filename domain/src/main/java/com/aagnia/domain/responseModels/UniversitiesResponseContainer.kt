package com.aagnia.domain.responseModels

import com.google.gson.annotations.SerializedName

data class UniversitiesResponseContainer(
    val domains: List<String>,
    val country: String,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("web_pages")
    val webPages: List<String>,
    val name: String,
    @SerializedName("state-province")
    val stateProvince: String?
)
