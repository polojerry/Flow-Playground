package com.samples.flow.domain.usecase

import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFriendsUseBaseCase = BaseUseCase<Unit, Flow<List<Friend>>>
class GetFriendsUseCase @Inject constructor(private val friendsRepository: FriendsRepository): GetFriendsUseBaseCase {
    override suspend fun invoke(params: Unit): Flow<List<Friend>> {
        return friendsRepository.getFriends()
    }
}