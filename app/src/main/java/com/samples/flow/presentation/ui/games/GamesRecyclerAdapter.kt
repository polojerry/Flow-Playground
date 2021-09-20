package com.samples.flow.presentation.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samples.flow.databinding.ItemGameBinding
import com.samples.flow.presentation.models.GamePresenter
import com.samples.flow.presentation.ui.games.GamesRecyclerAdapter.GameViewHolder.Companion.create

class GamesRecyclerAdapter :
    ListAdapter<GamePresenter, GamesRecyclerAdapter.GameViewHolder>(GameComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): GameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemGameBinding =
                    ItemGameBinding.inflate(layoutInflater, parent, false)

                return GameViewHolder(binding)
            }
        }

        fun bind(game: GamePresenter) {
            with(binding) {
                setGame(game)
                executePendingBindings()
            }
        }


    }

    class GameComparator : DiffUtil.ItemCallback<GamePresenter>() {
        override fun areItemsTheSame(oldItem: GamePresenter, newItem: GamePresenter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GamePresenter, newItem: GamePresenter): Boolean {
            return oldItem == newItem
        }

    }
}