package com.liam.udemypractice.repository.categorydetail

import com.liam.udemypractice.model.CategoryDetail
import com.liam.udemypractice.network.ApiClient

class CategoryDetailRemoteDataSource(private val apiClient: ApiClient): CategoryDetailDataSource {

    override suspend fun getCategoryDetail(categoryId: String?): CategoryDetail {

        return apiClient.getCategoryDetail(categoryId)
    }
}