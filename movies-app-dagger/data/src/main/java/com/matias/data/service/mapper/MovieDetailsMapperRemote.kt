package com.matias.data.service.mapper

import com.matias.data.service.response.MovieDetailsResponse
import com.matias.data.service.util.ApiUtils
import com.matias.domain.entity.MovieDetails

fun MovieDetails.mapToMovieDetailsRemote() =
    MovieDetailsResponse(
        adult = this.adult,
        backdropPath = this.backdropUrl?.let { ApiUtils.getPathFromImageUrl(it) },
        budget = this.budget,
        genres = this.genres.map { it.mapToGenreRemote() },
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterUrl?.let { ApiUtils.getPathFromImageUrl(it) },
        productionCompanies = this.productionCompanies.map { it.mapToProdCompanyRemote() },
        productionCountries = this.productionCountries.map { it.mapToProdCountryRemote() },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.mapToSpokenLangRemote() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

fun MovieDetails.Genre.mapToGenreRemote() =
    MovieDetailsResponse.Genre(id = this.id, name = this.name)

fun MovieDetails.ProductionCompany.mapToProdCompanyRemote() =
    MovieDetailsResponse.ProductionCompany(
        id = this.id,
        logoPath = this.logoUrl?.let { ApiUtils.getPathFromImageUrl(it) },
        name = this.name,
        originCountry = this.originCountry
    )

fun MovieDetails.ProductionCountry.mapToProdCountryRemote() =
    MovieDetailsResponse.ProductionCountry(
        iso31661 = this.iso31661,
        name = this.name
    )

fun MovieDetails.SpokenLanguage.mapToSpokenLangRemote() =
    MovieDetailsResponse.SpokenLanguage(iso6391 = this.iso6391, name = this.name)
