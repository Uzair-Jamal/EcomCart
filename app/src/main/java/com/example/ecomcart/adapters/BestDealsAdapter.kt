package com.example.ecomcart.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomcart.data.Product
import com.example.ecomcart.databinding.BestDealsRvItemBinding

class BestDealsAdapter(): RecyclerView.Adapter<BestDealsAdapter.BestDealsViewHolder>() {
    inner class BestDealsViewHolder(private val binding: BestDealsRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
           binding.apply{
               val imageUrl = product.images[0]
               Glide.with(itemView).load(imageUrl).into(imgBestDeal)
               product.offerPercentage?.let {
                   val remainingPrice = 1f - it
                   val priceAfterOffer = product.price * remainingPrice
                   tvNewPrice.text = "$ ${String.format("%.2f",priceAfterOffer)}"
                   tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
               }
               tvOldPrice.text = "$ ${product.price}"
               tvDealProductName.text = product.name
           }
        }
    }

    private val diffCallback = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return newItem == oldItem
        }
    }
        val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            BestDealsRvItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

}