package com.samples.flow.presentation.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.flow.domain.usecase.FetchUpcomingMoviesUseCase
import com.samples.flow.presentation.mappers.toPresentation
import com.samples.flow.presentation.models.MoviePresenter
import com.samples.flow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
) : ViewModel() {

    private val upComingMoviesUiSateValue =
        MutableStateFlow<UpComingMoviesState>(UpComingMoviesState.Loading)
    val upComingMoviesUiSate: StateFlow<UpComingMoviesState>
        get() = upComingMoviesUiSateValue.asStateFlow()

    init {
        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            fetchUpcomingMoviesUseCase(Unit)
                .catch {
                    upComingMoviesUiSateValue.emit(
                        UpComingMoviesState.Error(it.message.toString())
                    )
                }
                .collect { resource ->

                    when (resource) {
                        is Resource.Success -> {
                            upComingMoviesUiSateValue.emit(
                                UpComingMoviesState.Success(resource.data!!.map {
                                    it.toPresentation()
                                })
                            )
                        }
                        is Resource.Error -> {
                            upComingMoviesUiSateValue.emit(
                                UpComingMoviesState.Error(resource.message.toString())
                            )
                        }
                        else -> {
                        }
                    }
                }
        }
    }

}

sealed class UpComingMoviesState {
    data class Success(val news: List<MoviePresenter>) : UpComingMoviesState()
    data class Error(val message: String) : UpComingMoviesState()
    object Loading : UpComingMoviesState()
}