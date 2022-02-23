package com.liam.udemypractice.repository.home

import com.liam.udemypractice.model.HomeData

interface HomeDataSource {

    suspend fun getHomeData(): HomeData
}