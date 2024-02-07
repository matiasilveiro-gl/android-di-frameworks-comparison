package com.matias.domain.entity

data class MovieDetails(
    val adult: Boolean,
    val backdropUrl: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterUrl: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class ProductionCompany(
        val id: Int,
        val logoUrl: String?,
        val name: String,
        val originCountry: String
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String
    )

    data class SpokenLanguage(
        val iso6391: String,
        val name: String
    )
}
