package com.klinovvlad.task3klinov.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task3klinov.R
import com.klinovvlad.task3klinov.databinding.ItemMainBinding
import com.klinovvlad.task3klinov.db.UserDatabaseEntity

class MainAdapter(
    private val onItemClick: (item: UserDatabaseEntity) -> Unit,
    private val context: Context
) : ListAdapter<UserDatabaseEntity, MainAdapter.MainHolder>(MainUtil()) {

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
        fun bind(
            data: UserDatabaseEntity,
            onItemClick: (item: UserDatabaseEntity) -> Unit,
            context: Context
        ) {
            binding.textItemName.text = context.getString(
                R.string.full_name,
                data.title,
                data.first,
                data.last
            )
            binding.root.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    class MainUtil : DiffUtil.ItemCallback<UserDatabaseEntity>() {
        override fun areItemsTheSame(
            oldItem: UserDatabaseEntity,
            newItem: UserDatabaseEntity
        ): Boolean = oldItem.uuid == newItem.uuid

        override fun areContentsTheSame(
            oldItem: UserDatabaseEntity,
            newItem: UserDatabaseEntity
        ): Boolean = oldItem == newItem
    }

}