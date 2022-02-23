package com.liam.udemypractice.repository.categorydetail

import com.liam.udemypractice.model.CategoryDetail

interface CategoryDetailDataSource {

    suspend fun getCategoryDetail(categoryId: String?): CategoryDetail
}