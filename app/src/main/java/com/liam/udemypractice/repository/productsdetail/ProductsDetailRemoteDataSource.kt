package com.liam.udemypractice.repository.productsdetail

import com.liam.udemypractice.model.Product
import com.liam.udemypractice.network.ApiClient

class ProductsDetailRemoteDataSource (private val apiClient: ApiClient):
    ProductsDetailDataSource {

    override suspend fun getProductsDetail(productId: String): Product {

        return apiClient.getProductsDetail(productId)
    }
}