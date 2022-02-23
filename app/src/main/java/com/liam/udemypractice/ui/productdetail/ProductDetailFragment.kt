package com.liam.udemypractice.ui.productdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.liam.udemypractice.common.KEY_PRODUCT_ID
import com.liam.udemypractice.databinding.FragmentProductDetailBinding
import com.liam.udemypractice.ui.common.ViewModelFactory

class ProductDetailFragment: Fragment() {

    private val viewModel: ProductDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentProductDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setNavigation()
        requireArguments().getString(KEY_PRODUCT_ID)?.let { setLayout(it) }


    }

    private fun setNavigation() {
        binding.toolbarProductDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setLayout(productId: String) {
        viewModel.loadProductDetail(productId)
        val productImageAdapter = ProductDetailImageAdapter()

        binding.rvProductDetail.adapter = productImageAdapter
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.product = product
            productImageAdapter.submitList(product.descriptions)
        }
    }

    private fun setToolbar() {
        val productId = requireArguments().getString(KEY_PRODUCT_ID)

        Log.d("Detail", "productId=$productId")
        binding.btnToCart.text = productId
    }
}