package com.liam.udemypractice.repository.category

import com.liam.udemypractice.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}