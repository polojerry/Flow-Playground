package com.samples.flow.domain.usecase

import com.samples.flow.domain.models.Game
import com.samples.flow.domain.repository.GamesRepository
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias FetchGamesBaseUseCase = BaseUseCase<Unit, Flow<Resource<List<Game>>>>

class FetchGamesUseCase @Inject constructor(private val gamesRepository: GamesRepository) :
    FetchGamesBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Resource<List<Game>>> {
        return gamesRepository.fetchGames()
    }
}