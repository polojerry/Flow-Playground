package com.samples.flow.data.remote.util

import com.samples.flow.data.remote.util.Constants.THE_MOVIE_DB_IMAGE_BASE_URL

fun String.toMovieUrl(): String {
    return THE_MOVIE_DB_IMAGE_BASE_URL + this
}