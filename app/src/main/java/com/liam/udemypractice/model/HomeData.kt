package com.liam.udemypractice.model

import com.google.gson.annotations.SerializedName
import com.liam.udemypractice.Banner

data class HomeData(
    val title: Title,
    @SerializedName("top_banners") val topBanners: List<Banner>
)
