package com.liam.udemypractice.repository.home

import com.google.gson.Gson
import com.liam.udemypractice.AssetLoader
import com.liam.udemypractice.model.HomeData

class HomeAssetDataSource(private val assetLoader: AssetLoader) : HomeDataSource {

    private val gson = Gson()

    override fun getHomeData(): HomeData? {
        return assetLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, HomeData::class.java)
        }
    }
}