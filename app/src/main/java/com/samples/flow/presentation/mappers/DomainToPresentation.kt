package com.samples.flow.presentation.mappers

import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.models.Game
import com.samples.flow.domain.models.Movie
import com.samples.flow.presentation.models.FriendPresenter
import com.samples.flow.presentation.models.GamePresenter
import com.samples.flow.presentation.models.MoviePresenter

fun Game.toPresentation(): GamePresenter {
    return GamePresenter(
        imageUrl = imageUrl,
        name = name,
        genre = genre,
        id = id
    )
}

fun Friend.toPresentation(): FriendPresenter {
    return FriendPresenter(
        name = name
    )
}

fun Movie.toPresentation(): MoviePresenter {
    return MoviePresenter(
        title = title,
        imageUrl = imageUrl
    )
}