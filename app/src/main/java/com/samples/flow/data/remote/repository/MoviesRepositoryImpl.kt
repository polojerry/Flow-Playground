package com.samples.flow.data.remote.repository

import com.samples.flow.data.remote.api.MoviesApi
import com.samples.flow.data.remote.mappers.toDomain
import com.samples.flow.domain.models.Movie
import com.samples.flow.domain.repository.MovieRepository
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import timber.log.Timber
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi,
    private val refreshRate: Long = 5000
) : MovieRepository {
    override fun fetchUpcomingMovies(): Flow<Resource<List<Movie>>> = flow {
        try {
            val moviesResponse = api.fetchUpcomingMovies()
            when {
                moviesResponse.isSuccessful -> {
                    val movies = moviesResponse.body()!!.results
                    emit(
                        Resource.Success(
                            movies.map { movie ->
                                movie.toDomain()
                            })
                    )
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = "Network Error: Kindly Check our Internet"))
            Timber.e(e)
        } catch (e: Exception) {
            emit(Resource.Error(message = "Server Error"))
            Timber.e(e)
        }
    }
}