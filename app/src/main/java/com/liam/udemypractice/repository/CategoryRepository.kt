package com.liam.udemypractice.repository

import com.liam.udemypractice.model.Category

class CategoryRepository(

    private val remoteDataSource: CategoryRemoteDataSource
) {
    suspend fun getCategories(): List<Category> {
        return remoteDataSource.getCategories()
    }
}