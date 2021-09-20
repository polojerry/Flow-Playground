package com.samples.flow.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.samples.flow.R
import com.samples.flow.databinding.MoviesFragmentBinding
import com.samples.flow.presentation.models.MoviePresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MoviesFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var binding: MoviesFragmentBinding
    private lateinit var adapter: MoviesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(layoutInflater, container, false)
        binding.toolbarMovies.setOnMenuItemClickListener(this)

        collectUpcomingMovies()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDisplay()
    }

    private fun setUpDisplay() {
        adapter = MoviesRecyclerAdapter()
        binding.recyclerViewMovies.adapter = adapter
    }

    private fun collectUpcomingMovies() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.upComingMoviesUiSate.collect { uiState ->
                    when (uiState) {
                        is UpComingMoviesState.Success -> showSuccessState(uiState.news)
                        is UpComingMoviesState.Error -> showErrorState(uiState.message)
                        is UpComingMoviesState.Loading -> showLoadingState()
                    }

                }
            }
        }


    }

    private fun showLoadingState() {
        binding.progressMovies.isVisible = true
    }

    private fun showErrorState(message: String) {
        binding.progressMovies.isVisible = false
        Snackbar.make(binding.root, "An Error has occurred: $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun showSuccessState(movies: List<MoviePresenter>) {
        binding.progressMovies.isVisible = false
        adapter.submitList(movies)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_games -> {
                navigateTo(R.id.action_moviesFragment_to_gamesFragment)
                return true
            }

            R.id.action_friends -> {
                navigateTo(R.id.action_moviesFragment_to_friendsFragment)
                return true
            }

        }
        return false
    }

    private fun navigateTo(destinationAction: Int) {
        findNavController().navigate(destinationAction)
    }
}
