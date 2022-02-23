package com.liam.udemypractice.ui.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liam.udemypractice.databinding.ItemProductDetailImageBinding
import com.liam.udemypractice.model.Description

class ProductDetailImageAdapter :
    ListAdapter<Description, ProductDetailImageAdapter.ProductDetailImageViewHolder>(
        ProductImageDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailImageViewHolder {

        val binding =
            ItemProductDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductDetailImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDetailImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductDetailImageViewHolder(private val binding: ItemProductDetailImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(description: Description) {
            binding.description = description
            binding.executePendingBindings()
        }
    }
}

class ProductImageDiffCallback : DiffUtil.ItemCallback<Description>() {
    override fun areItemsTheSame(oldItem: Description, newItem: Description): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Description, newItem: Description): Boolean {
        return oldItem == newItem
    }

}