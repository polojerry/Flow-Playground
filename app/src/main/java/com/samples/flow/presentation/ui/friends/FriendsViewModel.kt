package com.samples.flow.presentation.ui.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.usecase.ClearFriendsUseCase
import com.samples.flow.domain.usecase.GetFriendsUseCase
import com.samples.flow.domain.usecase.InsertFriendUseCase
import com.samples.flow.presentation.mappers.toPresentation
import com.samples.flow.presentation.models.FriendPresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getFriendsUseCase: GetFriendsUseCase,
    private val insertFriendUseCase: InsertFriendUseCase,
    private val clearFriendsUseCase: ClearFriendsUseCase,
) : ViewModel() {

    private val friendsListUiStateValue =
        MutableStateFlow<FriendsListUiState>(FriendsListUiState.Loading)
    val friendsListUiState: StateFlow<FriendsListUiState>
        get() = friendsListUiStateValue.asStateFlow()

    init {
        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            getFriendsUseCase(Unit)
                .catch {
                    friendsListUiStateValue.emit(
                        FriendsListUiState.Error(it.message.toString())
                    )
                }
                .collect { friendsList ->
                    friendsListUiStateValue.emit(
                        FriendsListUiState.Success(friendsList.map {
                            it.toPresentation()
                        })
                    )
                }
        }
    }

    fun insertFriends(friend: Friend) {
        viewModelScope.launch {
            insertFriendUseCase(friend)
        }
    }

    fun clearFriends() {
        viewModelScope.launch {
            clearFriendsUseCase(Unit)
        }
    }

}

sealed class FriendsListUiState {
    data class Success(val friends: List<FriendPresenter>) : FriendsListUiState()
    data class Error(val message: String) : FriendsListUiState()
    object Loading : FriendsListUiState()
}
