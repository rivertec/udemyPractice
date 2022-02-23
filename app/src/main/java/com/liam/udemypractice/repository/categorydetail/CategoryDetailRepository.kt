package com.liam.udemypractice.repository.categorydetail

import com.liam.udemypractice.model.CategoryDetail

class CategoryDetailRepository(
    private val remoteDetailDataSource: CategoryDetailRemoteDataSource
) {

    suspend fun getCategoryDetail(categoryId: String): CategoryDetail {
        return remoteDetailDataSource.getCategoryDetail(categoryId)
    }
}