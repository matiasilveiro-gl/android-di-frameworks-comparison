package com.matias.data.service.mapper

import com.matias.data.service.response.MovieResponse
import com.matias.data.service.util.ApiUtils
import com.matias.domain.entity.Movie

fun Movie.mapToMovieRemote() =
    MovieResponse(
        adult = adult,
        backdropPath = backdropPath?.let { ApiUtils.getPathFromImageUrl(it) },
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterUrl?.let { ApiUtils.getPathFromImageUrl(it) },
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
