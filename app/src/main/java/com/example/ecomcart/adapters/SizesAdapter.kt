package com.example.ecomcart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomcart.databinding.SizeRvItemBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SizesAdapter() : RecyclerView.Adapter<SizesAdapter.SizesViewHolder>(){

    var selectedPosition = -1
    inner class SizesViewHolder(val binding: SizeRvItemBinding) : ViewHolder(binding.root) {
        fun bind(size: String, position: Int) {
            binding.tvSize.text = size
            if(selectedPosition == position){
                binding.imageShadow.visibility = View.VISIBLE
            }
            else{
                binding.imageShadow.visibility = View.INVISIBLE
            }
        }
    }

    private val diffCallback = object: DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
        return SizesViewHolder(SizeRvItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        val size = differ.currentList[position].toString()
        holder.bind(size, position)

        holder.itemView.setOnClickListener {
            if(selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(size)
        }
    }

    var onItemClick: ((String) -> Unit) ?= null

}