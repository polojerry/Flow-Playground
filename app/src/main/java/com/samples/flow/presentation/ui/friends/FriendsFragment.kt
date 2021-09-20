package com.samples.flow.presentation.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.samples.flow.R
import com.samples.flow.databinding.FriendsFragmentBinding
import com.samples.flow.domain.models.Friend
import com.samples.flow.presentation.models.FriendPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FriendsFragment : Fragment() {
    private val viewModel: FriendsViewModel by viewModels()

    private lateinit var binding: FriendsFragmentBinding
    private lateinit var adapter: FriendsRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FriendsFragmentBinding.inflate(layoutInflater, container, false)

        collectFriendsList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setUpDisplay()
    }

    private fun setUpDisplay() {
        adapter = FriendsRecyclerAdapter()
        binding.recyclerViewGames.adapter = adapter

    }


    private fun setClickListeners() {
        binding.buttonSaveFriend.setOnClickListener {
            if (inputValid()) {
                saveFriend()
            }
        }

        binding.toolbarFriends.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbarFriends.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_clear_friends) {
                viewModel.clearFriends()
            }
            true
        }
    }

    private fun saveFriend() {
        val name = binding.editTextFriendName.text.toString()
        viewModel.insertFriends(
            Friend(
                name = name
            )
        )
        binding.editTextFriendName.setText("")
    }

    private fun inputValid(): Boolean {
        return if (binding.editTextFriendName.text.isNullOrEmpty()) {
            binding.editTextFriendName.error = "Name is Required"
            return false
        } else {
            true
        }
    }


    private fun collectFriendsList() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.friendsListUiState.collect { uiState ->
                    when (uiState) {
                        is FriendsListUiState.Success -> showSuccessState(uiState.friends)
                        is FriendsListUiState.Error -> showErrorState(uiState.message)
                        is FriendsListUiState.Loading -> showLoadingState()
                    }

                }
            }
        }
    }

    private fun showLoadingState() {
        //TODO Show Loading
    }

    private fun showErrorState(message: String) {
        Snackbar.make(binding.root, "An Error has occurred: $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun showSuccessState(friends: List<FriendPresenter>) {
        adapter.submitList(friends)

    }
}