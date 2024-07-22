package com.example.ecomcart.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomcart.data.Product
import com.example.ecomcart.databinding.SpecialrvItemBinding

class SpecialProductsAdapter: RecyclerView.Adapter<SpecialProductsAdapter.SpecialProductsViewHolder>() {
    inner class SpecialProductsViewHolder(private val binding: SpecialrvItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return newItem.id == oldItem.id
        }

    }

    val differ = AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SpecialProductsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}