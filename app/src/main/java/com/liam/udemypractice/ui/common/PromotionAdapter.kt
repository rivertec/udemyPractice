package com.liam.udemypractice.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liam.udemypractice.databinding.ItemPromotionBinding
import com.liam.udemypractice.model.Product

class PromotionAdapter(private val clickListener: ProductClickListener):ListAdapter<Product, PromotionAdapter.CategoryPromotionViewHolder>(
    ProductDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPromotionViewHolder {
        val binding = ItemPromotionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryPromotionViewHolder(private val binding: ItemPromotionBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product) {
            binding.clickListener = clickListener
            binding.product = product
            binding.executePendingBindings()
        }
    }
}



