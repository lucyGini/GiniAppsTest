package com.example.giniappstest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giniappstest.data.network.Hit
import com.example.giniappstest.data.network.PixalImages
import com.example.giniappstest.databinding.RecyclerviewItemBinding
import com.example.giniappstest.util.DiffUtil

class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private var imageItemsList = emptyList<Hit>()

    class MyViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Hit) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return imageItemsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = imageItemsList[position]
        holder.bind(currentImage)

    }

    fun setData(newList: PixalImages) {
        val myDiffUtil = DiffUtil(imageItemsList, newList.hits)
        val diffUtilResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(myDiffUtil)
        imageItemsList = newList.hits
        diffUtilResult.dispatchUpdatesTo(this)

    }
}