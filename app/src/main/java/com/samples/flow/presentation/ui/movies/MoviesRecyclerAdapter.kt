package com.samples.flow.presentation.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samples.flow.databinding.ItemMovieBinding
import com.samples.flow.presentation.models.MoviePresenter
import com.samples.flow.presentation.ui.movies.MoviesRecyclerAdapter.MovieViewHolder.Companion.create

class MoviesRecyclerAdapter :
    ListAdapter<MoviePresenter, MoviesRecyclerAdapter.MovieViewHolder>(MovieComparator()) {

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemMovieBinding =
                    ItemMovieBinding.inflate(layoutInflater, parent, false)

                return MovieViewHolder(binding)
            }
        }

        fun bind(movie: MoviePresenter) {
            with(binding) {
                setMovie(movie)
                executePendingBindings()
            }
        }


    }

    class MovieComparator : DiffUtil.ItemCallback<MoviePresenter>() {
        override fun areItemsTheSame(oldItem: MoviePresenter, newItem: MoviePresenter): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: MoviePresenter, newItem: MoviePresenter): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}