package com.samples.flow.data.local.repository

import com.samples.flow.data.local.database.dao.FriendsDao
import com.samples.flow.data.local.mappers.toDomain
import com.samples.flow.data.local.mappers.toLocal
import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(private val friendsDao: FriendsDao) :
    FriendsRepository {
    override fun getFriends(): Flow<List<Friend>> {
        return friendsDao.getFriends().map { friendsList ->
            friendsList.map { friendsEntity ->
                friendsEntity.toDomain()

            }
        }
    }

    override suspend fun insertFriend(friends: Friend) {
        friendsDao.insert(friends.toLocal())
    }

    override suspend fun clearFriends() {
        friendsDao.clearFriends()
    }
}