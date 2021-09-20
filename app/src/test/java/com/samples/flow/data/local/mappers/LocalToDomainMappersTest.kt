package com.samples.flow.data.local.mappers

import com.google.common.truth.Truth.*
import com.samples.flow.data.fakes.Data
import org.junit.Test

class LocalToDomainMappersTest {

    @Test
    fun `When toDomain is called, the Local Entity should be Mapped to a Domain Value` (){

        val friend = Data.LocalFriend.friend.toDomain()
        assertThat(friend).isEqualTo(Data.DomainFriend.friend)
    }
}