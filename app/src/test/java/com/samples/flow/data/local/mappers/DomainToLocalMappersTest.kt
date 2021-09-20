package com.samples.flow.data.local.mappers

import com.google.common.truth.Truth.assertThat
import com.samples.flow.data.fakes.Data
import org.junit.Test

class DomainToLocalMappersTest {

    @Test
    fun `When toLocal is called, the Domain should be Mapped to a Local Entity`() {

        val friend = Data.DomainFriend.friend.toLocal()
        assertThat(friend).isEqualTo(Data.LocalFriend.friend)
    }
}