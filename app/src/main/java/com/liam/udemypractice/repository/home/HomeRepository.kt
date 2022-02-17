package com.liam.udemypractice.repository.home

import com.liam.udemypractice.model.HomeData

class HomeRepository(
    private val assetDataSource: HomeAssetDataSource
) {

    fun getHomeData(): HomeData? {
        return assetDataSource.getHomeData()
    }
}