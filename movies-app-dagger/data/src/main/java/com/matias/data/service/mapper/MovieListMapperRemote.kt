package com.matias.data.service.mapper

import com.matias.data.service.response.MovieListResponse
import com.matias.domain.entity.MovieList

fun MovieList.mapToMovieListRemote() =
    MovieListResponse(
        dates = dates?.let { MovieListResponse.Dates(it.maximum, it.minimum) },
        page = page,
        results = results.map { it.mapToMovieRemote() },
        totalPages = totalPages,
        totalResults = totalResults
    )
