package com.samples.flow.domain.repository

import com.samples.flow.domain.models.Friend
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {

    fun getFriends(): Flow<List<Friend>>

    suspend fun insertFriend(friends: Friend)

    suspend fun clearFriends()
}