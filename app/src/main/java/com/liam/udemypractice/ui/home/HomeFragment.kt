package com.liam.udemypractice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.liam.udemypractice.*
import com.liam.udemypractice.ui.common.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAppbarText = view.findViewById<TextView>(R.id.home_appbar_text)
        val homeAppbarLogo = view.findViewById<ImageView>(R.id.home_appbar_logo)

        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        viewModel.title.observe(viewLifecycleOwner, { title ->
            homeAppbarText.text = title.text
            GlideApp
                .with(this)
                .load(title.iconUrl)
                .into(homeAppbarLogo)
        })

        viewpager.adapter = HomeBannerAdapter().apply {

            viewModel.topBanners.observe(viewLifecycleOwner, { banners ->
                submitList(banners)
            })
        }

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
    }
}