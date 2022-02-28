package com.example.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.database.Model
import com.example.roomdatabase.databinding.DisplayRecycularViewBinding

class DataAdapter :ListAdapter<Model,DataAdapter.ViewHolder>(DataDiffCallBack()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: DisplayRecycularViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Model
        ) {
            binding.data = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DisplayRecycularViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class DataDiffCallBack : DiffUtil.ItemCallback<Model>(){
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.Id == newItem.Id
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem == newItem
    }

}


