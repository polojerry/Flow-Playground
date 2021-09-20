package com.samples.flow.data.remote

import com.google.common.truth.Truth
import com.samples.flow.data.remote.util.toMovieUrl
import org.junit.Test

class StringToMovieUrlTest {

    @Test
    fun `Should Convert Poster Image String into a Image Url`() {
        val posterUrl = "/kv2Qk9MKFFQo4WQPaYta599HkJP.jpg"
        val imageUrl = "https://image.tmdb.org/t/p/w500/kv2Qk9MKFFQo4WQPaYta599HkJP.jpg"

        Truth.assertThat(posterUrl.toMovieUrl()).isEqualTo(imageUrl)
    }
}