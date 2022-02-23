package com.liam.udemypractice.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liam.udemypractice.model.Product
import com.liam.udemypractice.repository.productsdetail.ProductsDetailRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productsDetailRepository: ProductsDetailRepository,

) :ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product


    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            val productDetails = productsDetailRepository.getProductsDetail(productId)
            _product.value = productDetails
        }
    }
}