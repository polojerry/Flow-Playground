package com.samples.flow.data.remote.models

data class MovieResponse(
    val page: Int,
    val results: List<MovieNetwork>
)