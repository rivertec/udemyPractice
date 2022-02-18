package com.liam.udemypractice.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.liam.udemypractice.R
import com.liam.udemypractice.common.KEY_CATEGORY_ID
import com.liam.udemypractice.common.KEY_CATEGORY_LABEL
import com.liam.udemypractice.databinding.FragmentCategoryBinding
import com.liam.udemypractice.ui.common.EventObserver
import com.liam.udemypractice.ui.common.ViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel: CategoryViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel)
        binding.rvCategoryList.adapter = categoryAdapter
        viewModel.items.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        viewModel.openCategoryEvent.observe(viewLifecycleOwner, EventObserver {
            openCategoryDetail(it.categoryId, it.label)
        })
    }

    private fun openCategoryDetail(categoryID: String, categoryLabel: String) {
        findNavController().navigate(R.id.action_category_to_category_detail, bundleOf(
            KEY_CATEGORY_ID to categoryID,
            KEY_CATEGORY_LABEL to categoryLabel
        ))
    }
}