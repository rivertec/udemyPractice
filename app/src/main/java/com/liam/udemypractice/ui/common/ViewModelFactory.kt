package com.liam.udemypractice.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liam.udemypractice.ServiceLocator
import com.liam.udemypractice.repository.category.CategoryRemoteDataSource
import com.liam.udemypractice.repository.category.CategoryRepository
import com.liam.udemypractice.repository.categorydetail.CategoryDetailRemoteDataSource
import com.liam.udemypractice.repository.categorydetail.CategoryDetailRepository
import com.liam.udemypractice.repository.home.HomeRemoteDataSource
import com.liam.udemypractice.repository.home.HomeRepository
import com.liam.udemypractice.repository.productsdetail.ProductsDetailRemoteDataSource
import com.liam.udemypractice.repository.productsdetail.ProductsDetailRepository
import com.liam.udemypractice.ui.category.CategoryViewModel
import com.liam.udemypractice.ui.categorydetaill.CategoryDetailViewModel
import com.liam.udemypractice.ui.home.HomeViewModel
import com.liam.udemypractice.ui.productdetail.ProductDetailViewModel

class ViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeRemoteDataSource(ServiceLocator.provideApiClient()))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ServiceLocator.provideApiClient()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository = CategoryDetailRepository(CategoryDetailRemoteDataSource(ServiceLocator.provideApiClient()))
                CategoryDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> {
                val repository = ProductsDetailRepository(ProductsDetailRemoteDataSource(ServiceLocator.provideApiClient()))
                ProductDetailViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}