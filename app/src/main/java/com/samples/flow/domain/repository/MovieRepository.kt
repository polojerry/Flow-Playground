package com.samples.flow.domain.repository

import com.samples.flow.domain.models.Movie
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchUpcomingMovies(): Flow<Resource<List<Movie>>>

}