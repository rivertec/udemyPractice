package com.liam.udemypractice.repository

import com.liam.udemypractice.model.Category
import com.liam.udemypractice.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient): CategoryDataSource {

    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}