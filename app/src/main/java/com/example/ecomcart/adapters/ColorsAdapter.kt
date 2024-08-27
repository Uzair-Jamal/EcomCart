package com.example.ecomcart.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ecomcart.databinding.ColorRvItemBinding

class ColorsAdapter: RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

    private var selectedPosition = -1
    inner class ColorsViewHolder(val binding: ColorRvItemBinding): ViewHolder(binding.root) {

        fun bind(color: Int, position: Int) {
            val imageDrawable = ColorDrawable(color)
            binding.imageColor.setImageDrawable(imageDrawable)
            if(position == selectedPosition ){ //Color is picked
                binding.imageShadow.visibility = View.VISIBLE
                binding.imagePicked.visibility = View.VISIBLE
            }
            else{  //Color is not picked
                binding.imageShadow.visibility = View.INVISIBLE
                binding.imagePicked.visibility = View.INVISIBLE
            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        return ColorsViewHolder(ColorRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val color = differ.currentList[position]
        holder.bind(color, position)

        holder.itemView.setOnClickListener {
            if(selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(color)
        }
    }

    var onItemClick:((Int) -> Unit)? = null

}