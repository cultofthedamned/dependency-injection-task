package com.klinovvlad.task3klinov.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task3klinov.databinding.ItemMainBinding
import com.klinovvlad.task3klinov.model.DataTest

class MainAdapter : ListAdapter<DataTest, MainAdapter.MainHolder>(MainUtil()) {

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
        holder.bind(currentList[position], position)
    }

    class MainHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataTest, position: Int) {
            binding.textItemName.text =
                "${data.results[position].name.title}"
        }
    }

    class MainUtil : DiffUtil.ItemCallback<DataTest>() {
        override fun areItemsTheSame(oldItem: DataTest, newItem: DataTest): Boolean =
            oldItem.results == newItem.results

        override fun areContentsTheSame(oldItem: DataTest, newItem: DataTest): Boolean =
            oldItem == newItem
    }

}