package com.samples.flow.data.remote.mappers

import com.samples.flow.data.remote.models.MovieNetwork
import com.samples.flow.data.remote.models.GameNetwork
import com.samples.flow.data.remote.util.toMovieUrl
import com.samples.flow.domain.models.Game
import com.samples.flow.domain.models.Movie

fun GameNetwork.toDomain(): Game {
    return Game(
        name = name,
        imageUrl = image_url,
        genre = genre,
        id = id
    )
}

fun MovieNetwork.toDomain(): Movie {
    return Movie(
        title = title,
        imageUrl = posterPath?.toMovieUrl().toString()
    )
}
