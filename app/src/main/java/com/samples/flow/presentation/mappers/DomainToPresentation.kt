package com.samples.flow.presentation.mappers

import com.samples.flow.domain.models.Game
import com.samples.flow.presentation.models.GamePresenter

fun Game.toPresentation(): GamePresenter {
    return GamePresenter(
        imageUrl = imageUrl,
        name = name,
        genre = genre,
        id = id
    )
}