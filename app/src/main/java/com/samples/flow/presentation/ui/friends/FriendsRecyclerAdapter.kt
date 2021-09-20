package com.samples.flow.presentation.ui.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samples.flow.databinding.ItemFriendBinding
import com.samples.flow.presentation.models.FriendPresenter
import com.samples.flow.presentation.ui.friends.FriendsRecyclerAdapter.FriendViewHolder.Companion.create

class FriendsRecyclerAdapter :
    ListAdapter<FriendPresenter, FriendsRecyclerAdapter.FriendViewHolder>(FriendComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class FriendViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): FriendViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemFriendBinding =
                    ItemFriendBinding.inflate(layoutInflater, parent, false)

                return FriendViewHolder(binding)
            }
        }

        fun bind(friend: FriendPresenter) {
            with(binding) {
                setFriend(friend)
                executePendingBindings()
            }
        }


    }

    class FriendComparator : DiffUtil.ItemCallback<FriendPresenter>() {
        override fun areItemsTheSame(oldItem: FriendPresenter, newItem: FriendPresenter): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: FriendPresenter,
            newItem: FriendPresenter
        ): Boolean {
            return oldItem == newItem
        }

    }
}