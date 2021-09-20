package com.samples.flow.presentation.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.flow.domain.usecase.FetchGamesUseCase
import com.samples.flow.presentation.mappers.toPresentation
import com.samples.flow.presentation.models.GamePresenter
import com.samples.flow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val fetchGamesUseCase: FetchGamesUseCase,
) : ViewModel() {

    private val gamesUiStateValue =
        MutableStateFlow<GamesState>(GamesState.Loading)
    val gamesUiState: StateFlow<GamesState>
        get() = gamesUiStateValue.asStateFlow()

    init {
        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            fetchGamesUseCase(Unit)
                .catch {
                    gamesUiStateValue.emit(
                        GamesState.Error(it.message.toString())
                    )
                }
                .collect { resource ->

                    when (resource) {
                        is Resource.Success -> {
                            gamesUiStateValue.emit(
                                GamesState.Success(resource.data!!.map {
                                    it.toPresentation()
                                })
                            )
                        }
                        is Resource.Error -> {
                            gamesUiStateValue.emit(
                                GamesState.Error(resource.message.toString())
                            )
                        }
                        else -> {
                        }
                    }
                }
        }
    }
}

sealed class GamesState {
    data class Success(val games: List<GamePresenter>) : GamesState()
    data class Error(val message: String) : GamesState()
    object Loading : GamesState()
}