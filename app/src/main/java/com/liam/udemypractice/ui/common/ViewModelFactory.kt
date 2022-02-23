package com.liam.udemypractice.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liam.udemypractice.AssetLoader
import com.liam.udemypractice.network.ApiClient
import com.liam.udemypractice.repository.category.CategoryRemoteDataSource
import com.liam.udemypractice.repository.category.CategoryRepository
import com.liam.udemypractice.repository.categorydetail.CategoryDetailRemoteDataSource
import com.liam.udemypractice.repository.categorydetail.CategoryDetailRepository
import com.liam.udemypractice.repository.home.HomeAssetDataSource
import com.liam.udemypractice.repository.home.HomeRepository
import com.liam.udemypractice.ui.category.CategoryViewModel
import com.liam.udemypractice.ui.categorydetaill.CategoryDetailViewModel
import com.liam.udemypractice.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context, private vararg val categoryId: String?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository =
                    CategoryDetailRepository(CategoryDetailRemoteDataSource(ApiClient.create()))
                CategoryDetailViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}