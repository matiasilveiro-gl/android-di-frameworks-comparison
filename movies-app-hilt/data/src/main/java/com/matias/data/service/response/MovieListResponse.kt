package com.matias.data.service.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("dates") val dates: Dates?,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    data class Dates(
        @SerializedName("maximum") val maximum: String,
        @SerializedName("minimum") val minimum: String
    )
}
