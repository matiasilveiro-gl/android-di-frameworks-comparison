package com.matias.domain.service

import com.matias.domain.entity.Movie
import com.matias.domain.entity.MovieDetails
import com.matias.domain.util.ResultOf

interface MoviesService {

    suspend fun getMovieById(id: Int): ResultOf<MovieDetails>

    suspend fun getNowPlayingMovies(): ResultOf<List<Movie>>
}
