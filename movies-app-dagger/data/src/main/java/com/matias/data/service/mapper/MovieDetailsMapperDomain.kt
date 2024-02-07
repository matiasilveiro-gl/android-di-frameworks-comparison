package com.matias.data.service.mapper

import com.matias.data.service.response.MovieDetailsResponse
import com.matias.data.service.util.ApiUtils
import com.matias.domain.entity.MovieDetails

fun MovieDetailsResponse.mapToMovieDetailsDomain() =
    MovieDetails(
        adult = this.adult,
        backdropUrl = this.backdropPath?.let { ApiUtils.getImageUrlFromPath(it) },
        budget = this.budget,
        genres = this.genres.map { it.mapToGenreDomain() },
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterUrl = this.posterPath?.let { ApiUtils.getImageUrlFromPath(it) },
        productionCompanies = this.productionCompanies.map { it.mapToProdCompanyDomain() },
        productionCountries = this.productionCountries.map { it.mapToProdCountryDomain() },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.mapToSpokenLangDomain() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

fun MovieDetailsResponse.Genre.mapToGenreDomain() =
    MovieDetails.Genre(id = this.id, name = this.name)

fun MovieDetailsResponse.ProductionCompany.mapToProdCompanyDomain() =
    MovieDetails.ProductionCompany(
        id = this.id,
        logoUrl = this.logoPath?.let { ApiUtils.getImageUrlFromPath(it) },
        name = this.name,
        originCountry = this.originCountry
    )

fun MovieDetailsResponse.ProductionCountry.mapToProdCountryDomain() =
    MovieDetails.ProductionCountry(
        iso31661 = this.iso31661,
        name = this.name
    )

fun MovieDetailsResponse.SpokenLanguage.mapToSpokenLangDomain() =
    MovieDetails.SpokenLanguage(iso6391 = this.iso6391, name = this.name)
