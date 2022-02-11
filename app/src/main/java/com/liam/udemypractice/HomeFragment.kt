package com.liam.udemypractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//        button.setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }

        val homeAppbarText = view.findViewById<TextView>(R.id.home_appbar_text)
        val homeAppbarLogo = view.findViewById<ImageView>(R.id.home_appbar_logo)

        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")

        if (!homeJsonString.isNullOrEmpty()) {

            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

            homeAppbarText.text = homeData.title.text
            GlideApp.with(this).load(homeData.title.iconUrl).into(homeAppbarLogo)

            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
            }

//            viewpager.setPageTransformer(object : ViewPager2.PageTransformer {
//                override fun transformPage(page: View, position: Float) {
//                    TODO("Not yet implemented")
//                }
//            })

            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            viewpager.setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            viewpager.offscreenPageLimit = 3

            TabLayoutMediator(viewpagerIndicator, viewpager) { tab, position ->

            }.attach()


//            val jsonObject = JSONObject(homeJsonString)
//            val title = jsonObject.getJSONObject("title")
//            val text = title.getString("text")
//            val iconUrl = title.getString("icon_url")
//
//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val firstBanner = topBanners.getJSONObject(0)
//            val productDetail = firstBanner.getJSONObject("product_detail")
//            val price = productDetail.getInt("price")
//
//            val titleValue = Title(text, iconUrl)
//            val size = topBanners.length()
//            for (index in 0 until size) {
//                val bannerObject = topBanners.getJSONObject(index)
//
//                val backgroundImageUrl = bannerObject.getString("background_image_url")
//
//                val badgeObject = bannerObject.getJSONObject("badge")
//                val badgeLabel = badgeObject.getString("label")
//                val badgeBackgroundColor = badgeObject.getString("background_color")
//                val bannerBadge = BannerBadge(badgeLabel, badgeBackgroundColor)
//
//                val bannerLabel = bannerObject.getString("label")
//
//                val productDetailObject = bannerObject.getJSONObject("product_detail")
//                val pDetailBrandName = productDetailObject.getString("brand_name")
//                val pDetailLabel = productDetailObject.getString("label")
//                val pDetailDiscountRate = productDetailObject.getInt("discount_rate")
//                val pDetailPrice = productDetailObject.getInt("price")
//                val pDetailThumbnailUrl = productDetailObject.getString("thumbnail_image_url")
//                val pDetailProductID = productDetailObject.getString("product_id")
//                val bannerPDetail = BannerProductDetail(pDetailBrandName,pDetailLabel,pDetailDiscountRate,pDetailPrice,pDetailThumbnailUrl,pDetailProductID)
//
//                val banner = Banner(
//                    backgroundImageUrl,
//                    bannerBadge,
//                    bannerLabel,
//                    bannerPDetail,
//                )
//            }

        }
    }
}