package com.liam.udemypractice.ui.categorydetaill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.liam.udemypractice.common.KEY_CATEGORY_ID
import com.liam.udemypractice.common.KEY_CATEGORY_LABEL
import com.liam.udemypractice.databinding.FragmentCategoryDetailBinding
import com.liam.udemypractice.ui.common.ViewModelFactory

class CategoryDetailFragment: Fragment() {

    private lateinit var binding: FragmentCategoryDetailBinding
    private val viewModel: CategoryDetailViewModel by viewModels { ViewModelFactory(requireContext())}

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setNavigation()
        val categoryId = requireArguments().getString(KEY_CATEGORY_ID)
        categoryId?.let { setListAdapter(it) }

    }

    private fun setNavigation() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
        binding.toolbarCategoryDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setListAdapter(categoryId: String?) {
        val titleAdapter = CategorySectionTitleAdapter()
        val topSellingSectionAdapter = CategoryTopSellingAdapter()
        val promotionAdapter = PromotionAdapter()
        viewModel.loadCategoryDetail(categoryId)

        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner) { topSelling ->
            topSellingSectionAdapter.submitList(listOf(topSelling))
        }
        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }
}