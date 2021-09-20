package com.samples.flow.presentation.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.samples.flow.databinding.GamesFragmentBinding
import com.samples.flow.presentation.models.GamePresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GamesFragment : Fragment() {

    private val viewModel: GamesViewModel by viewModels()

    private lateinit var binding: GamesFragmentBinding
    private lateinit var adapter: GamesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GamesFragmentBinding.inflate(layoutInflater, container, false)
        collectGames()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setUpDisplay()
    }

    private fun setUpDisplay() {
        adapter = GamesRecyclerAdapter()
        binding.recyclerViewGames.adapter = adapter
    }

    private fun setClickListeners() {
        binding.toolbarMovies.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun collectGames() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gamesUiState.collect { uiState ->
                    when (uiState) {
                        is GamesState.Success -> showSuccessState(uiState.games)
                        is GamesState.Error -> showErrorState(uiState.message)
                        is GamesState.Loading -> showLoadingState()
                    }

                }
            }
        }
    }

    private fun showLoadingState() {
        binding.progressGame.isVisible = true
    }

    private fun showErrorState(message: String) {
        binding.progressGame.isVisible = false
        Snackbar.make(binding.root, "An Error has occurred: $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun showSuccessState(movies: List<GamePresenter>) {
        binding.progressGame.isVisible = false
        adapter.submitList(movies)

    }


}