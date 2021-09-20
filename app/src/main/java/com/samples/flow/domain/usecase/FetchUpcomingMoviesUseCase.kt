package com.samples.flow.domain.usecase

import com.samples.flow.domain.models.Movie
import com.samples.flow.domain.repository.MovieRepository
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias FetchUpcomingMoviesBaseCase = BaseUseCase<Unit, Flow<Resource<List<Movie>>>>

class FetchUpcomingMoviesUseCase @Inject constructor(private val moviesRepository: MovieRepository) :
    FetchUpcomingMoviesBaseCase {
    override suspend fun invoke(params: Unit): Flow<Resource<List<Movie>>> {
        return moviesRepository.fetchUpcomingMovies()
    }
}