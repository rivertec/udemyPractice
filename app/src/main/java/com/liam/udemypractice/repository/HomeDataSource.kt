package com.liam.udemypractice.repository

import com.liam.udemypractice.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}