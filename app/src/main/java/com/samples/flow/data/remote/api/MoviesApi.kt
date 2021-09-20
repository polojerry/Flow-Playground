package com.samples.flow.data.remote.api

import com.samples.flow.data.remote.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("upcoming")
   suspend fun fetchUpcomingMovies(
        @Query("api_key") api_key: String = "76a1fa6b5a2ce564c1ad4a60e216c331"
    ): Response<MovieResponse>
}