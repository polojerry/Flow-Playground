package com.samples.flow.data.remote.models

import com.squareup.moshi.Json

data class MovieNetwork(

    val title: String,

    @field:Json(name = "poster_path")
    val posterPath: String?,

    )
