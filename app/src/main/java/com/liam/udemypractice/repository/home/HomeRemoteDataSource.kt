package com.liam.udemypractice.repository.home

import com.liam.udemypractice.model.HomeData
import com.liam.udemypractice.network.ApiClient

class HomeRemoteDataSource(private val apiClient: ApiClient): HomeDataSource {

    override suspend fun getHomeData(): HomeData {
        return apiClient.getHomeData()
    }
}