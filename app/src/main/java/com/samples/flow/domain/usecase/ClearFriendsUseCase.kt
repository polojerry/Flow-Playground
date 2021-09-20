package com.samples.flow.domain.usecase

import com.samples.flow.domain.repository.FriendsRepository
import javax.inject.Inject

typealias ClearFriendsBaseCase = BaseUseCase<Unit, Unit>

class ClearFriendsUseCase @Inject constructor(private val friendsRepository: FriendsRepository) :
    ClearFriendsBaseCase {
    override suspend fun invoke(params: Unit) {
        return friendsRepository.clearFriends()
    }
}