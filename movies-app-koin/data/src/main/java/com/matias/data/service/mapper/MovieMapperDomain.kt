package com.matias.data.service.mapper

import com.matias.data.service.response.MovieResponse
import com.matias.data.service.util.ApiUtils
import com.matias.domain.entity.Movie

fun MovieResponse.mapToMovieDomain() =
    Movie(
        adult = adult,
        backdropPath = backdropPath?.let { ApiUtils.getImageUrlFromPath(it) },
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterUrl = posterPath?.let { ApiUtils.getImageUrlFromPath(it) },
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
