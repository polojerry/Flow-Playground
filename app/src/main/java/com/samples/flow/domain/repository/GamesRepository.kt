package com.samples.flow.domain.repository

import com.samples.flow.domain.models.Game
import com.samples.flow.util.Resource
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun fetchGames(): Flow<Resource<List<Game>>>
}