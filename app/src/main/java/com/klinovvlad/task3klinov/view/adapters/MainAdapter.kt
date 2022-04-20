package com.klinovvlad.task3klinov.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task3klinov.databinding.ItemMainBinding
import com.klinovvlad.task3klinov.model.UserNetworkEntity.UserResults

class MainAdapter(
    private val onItemClick: (item: UserResults) -> Unit
) : ListAdapter<UserResults, MainAdapter.MainHolder>(MainUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    class MainHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResults, onItemClick: (item: UserResults) -> Unit) {
            binding.textItemName.text =
                "${data.name.title} ${data.name.first} ${data.name.last}"
            binding.root.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    class MainUtil : DiffUtil.ItemCallback<UserResults>() {
        override fun areItemsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem.email == newItem.email

        override fun areContentsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem == newItem
    }

}