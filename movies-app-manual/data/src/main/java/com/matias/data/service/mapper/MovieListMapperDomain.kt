package com.matias.data.service.mapper

import com.matias.data.service.response.MovieListResponse
import com.matias.domain.entity.MovieList

fun MovieListResponse.mapToMovieListDomain() =
    MovieList(
        dates = dates?.let { MovieList.Dates(it.maximum, it.minimum) },
        page = page,
        results = results.map { it.mapToMovieDomain() },
        totalPages = totalPages,
        totalResults = totalResults
    )
