package com.klinovvlad.task3klinov.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task3klinov.R
import com.klinovvlad.task3klinov.databinding.ItemMainBinding
import com.klinovvlad.task3klinov.model.UserNetworkEntity.UserResults

class MainAdapter(
    private val onItemClick: (item: UserResults) -> Unit,
    private val context: Context
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
        holder.bind(currentList[position], onItemClick, context)
    }

    class MainHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResults, onItemClick: (item: UserResults) -> Unit, context: Context) {
            binding.textItemName.text = context.getString(
                R.string.full_name,
                data.name.title,
                data.name.first,
                data.name.last
            )
            binding.root.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    class MainUtil : DiffUtil.ItemCallback<UserResults>() {
        override fun areItemsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem.login.uuid == newItem.login.uuid

        override fun areContentsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem == newItem
    }

}