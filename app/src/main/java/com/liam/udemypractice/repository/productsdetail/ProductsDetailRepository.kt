package com.liam.udemypractice.repository.productsdetail


import com.liam.udemypractice.model.Product

class ProductsDetailRepository(
    private val remoteDetailDataSource: ProductsDetailRemoteDataSource
) {
    suspend fun getProductsDetail(productId: String): Product {
        return remoteDetailDataSource.getProductsDetail(productId)
    }
}