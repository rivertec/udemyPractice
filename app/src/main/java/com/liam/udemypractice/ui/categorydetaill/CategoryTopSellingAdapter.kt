package com.liam.udemypractice.ui.categorydetaill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liam.udemypractice.databinding.ItemCategoryTopSellingSectionBinding
import com.liam.udemypractice.model.TopSelling

class CategoryTopSellingAdapter :
    ListAdapter<TopSelling, CategoryTopSellingAdapter.TopSellingViewHolder>(TopSellingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellingViewHolder {
        val binding = ItemCategoryTopSellingSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopSellingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopSellingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TopSellingViewHolder(private val binding: ItemCategoryTopSellingSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val nestedAdapter = CategoryTopSellingItemAdapter()

        init {
            binding.rvCategorySection.adapter = nestedAdapter
        }

        fun bind(topSelling: TopSelling) {
            binding.title = topSelling.title
            binding.executePendingBindings()
            nestedAdapter.submitList(topSelling.categories)
        }
    }
}

class TopSellingDiffCallback : DiffUtil.ItemCallback<TopSelling>() {
    override fun areItemsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem.title?.text == newItem.title?.text
    }

    override fun areContentsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem == newItem
    }

}
