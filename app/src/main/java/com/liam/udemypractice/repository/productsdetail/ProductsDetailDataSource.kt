package com.liam.udemypractice.repository.productsdetail

import com.liam.udemypractice.model.Product

interface ProductsDetailDataSource {

    suspend fun getProductsDetail(productId: String): Product
}