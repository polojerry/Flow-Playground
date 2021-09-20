package com.samples.flow.data.fakes

import com.samples.flow.data.local.database.model.FriendsEntity
import com.samples.flow.domain.models.Friend

class Data {

    object LocalFriend {
        val friend = FriendsEntity(
            "Jeremiah"
        )
        val friend1 = FriendsEntity(
            name = "Lucy"
        )
    }

    object DomainFriend {
        val friend = Friend(
            "Jeremiah"
        )
        val friend1 = Friend(
            name = "Lucy"
        )
    }
}