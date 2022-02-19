package com.liam.udemypractice.repository.categorydetail

import com.liam.udemypractice.model.CategoryDetail

class CategoryDetailRepository(
    private val remoteDetailDataSource: CategoryDetailRemoteDataSource
) {

    suspend fun getCategoryDetail(): CategoryDetail {
        return remoteDetailDataSource.getCategoryDetail()
    }
}