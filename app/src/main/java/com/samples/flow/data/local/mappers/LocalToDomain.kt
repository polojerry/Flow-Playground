package com.samples.flow.data.local.mappers

import com.samples.flow.data.local.database.model.FriendsEntity
import com.samples.flow.domain.models.Friend

fun FriendsEntity.toDomain() : Friend {
    return Friend(
        name = name
    )
}