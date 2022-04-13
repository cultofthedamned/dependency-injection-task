package com.klinovvlad.task3klinov.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task3klinov.databinding.ItemMainBinding
import com.klinovvlad.task3klinov.model.DataResult
import com.klinovvlad.task3klinov.model.DataTest

class MainAdapter(private val onItemClick: (item: DataResult) -> Unit
) : ListAdapter<DataResult, MainAdapter.MainHolder>(MainUtil()) {

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
        fun bind(data: DataResult, onItemClick: (item: DataResult) -> Unit) {
            binding.textItemName.text =
                "${data.name.title} ${data.name.first} ${data.name.last}"
            binding.root.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    class MainUtil : DiffUtil.ItemCallback<DataResult>() {
        override fun areItemsTheSame(oldItem: DataResult, newItem: DataResult): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: DataResult, newItem: DataResult): Boolean =
            oldItem == newItem
    }

}