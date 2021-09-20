package com.samples.flow.data.remote.repositories

import com.google.common.truth.Truth.assertThat
import com.samples.flow.data.remote.repository.MoviesRepositoryImpl
import com.samples.flow.domain.repository.MovieRepository
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import timber.log.Timber

open class MoviesRepositoryImplTest : BaseTest() {

    lateinit var movieRepository: MovieRepository

    @Before
    override fun setup() {
        super.setup()
        movieRepository = MoviesRepositoryImpl(api = moviesApi)
    }

    @Test
    fun `when fetch upcoming movies is invoked, a list of movies should be returned`() =
        runBlocking {
            val upcomingMovies = movieRepository.fetchUpcomingMovies()
            upcomingMovies.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        assertThat(resource.data).isNotEmpty()
                    }
                    is Resource.Error -> {
                        Timber.d(resource.message)
                    }
                    else -> {
                    }
                }

            }
        }
}