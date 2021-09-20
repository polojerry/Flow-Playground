package com.samples.flow.domain.usecase

import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.repository.FriendsRepository
import javax.inject.Inject

typealias InsertFriendBaseUseCase = BaseUseCase<Friend, Unit>

class InsertFriendUseCase @Inject constructor(private val friendsRepository: FriendsRepository) :
    InsertFriendBaseUseCase {
    override suspend fun invoke(params: Friend) {
        friendsRepository.insertFriend(params)
    }
}