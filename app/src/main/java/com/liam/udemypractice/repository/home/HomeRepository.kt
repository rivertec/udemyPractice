package com.liam.udemypractice.repository.home

import com.liam.udemypractice.model.HomeData

class HomeRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource
) {

    suspend fun getHomeData(): HomeData {
        return homeRemoteDataSource.getHomeData()
    }
}