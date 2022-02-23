package com.liam.udemypractice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.liam.udemypractice.R
import com.liam.udemypractice.common.KEY_PRODUCT_ID
import com.liam.udemypractice.databinding.FragmentHomeBinding
import com.liam.udemypractice.ui.categorydetaill.SectionTitleAdapter
import com.liam.udemypractice.ui.common.EventObserver
import com.liam.udemypractice.ui.common.ProductClickListener
import com.liam.udemypractice.ui.common.PromotionAdapter
import com.liam.udemypractice.ui.common.ViewModelFactory

class HomeFragment : Fragment(), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setTopBanners()
        setNavigation()
        setPromotions()
    }

    private fun setPromotions() {

        val promotionAdapter = PromotionAdapter(this)
        val sectionTitleAdapter = SectionTitleAdapter()
        binding.rvHomePromotions.adapter = ConcatAdapter(sectionTitleAdapter, promotionAdapter)
        viewModel.promotion.observe(viewLifecycleOwner){ promotion ->
            promotionAdapter.submitList(promotion.items)
            sectionTitleAdapter.submitList(listOf(promotion.title))
        }
    }

    private fun setNavigation() {
        viewModel.openDetailEvent.observe(viewLifecycleOwner, EventObserver { productId ->
            findNavController().navigate(
                R.id.action_home_to_product_detail, bundleOf(KEY_PRODUCT_ID to productId)
            )
        })
    }

    private fun setToolbar() {
        viewModel.homeData.observe(viewLifecycleOwner) { homeData ->
            binding.home = homeData
        }
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    submitList(banners)
                }
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            offscreenPageLimit = 3

            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { _, _ ->

            }.attach()
        }
    }

    override fun onProductClick(productId: String) {
        findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
            KEY_PRODUCT_ID to "desk-1"
        ))
    }
}