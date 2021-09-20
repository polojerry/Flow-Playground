package com.samples.flow.data.remote.mappers

import com.samples.flow.data.remote.models.GameNetwork
import com.samples.flow.domain.models.Game

fun GameNetwork.toDomain(): Game {
    return Game(
        name = name,
        imageUrl = image_url,
        genre = genre,
        id = id
    )
}

