package com.example.e_comm.ui.fragments.shop.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_comm.R
import com.example.e_comm.data.utils.Resource
import com.example.e_comm.databinding.FragmentMainHomeCategoryBinding
import com.example.e_comm.ui.adapters.SpecialProductsAdapter
import com.example.e_comm.ui.fragments.shop.home.MainHomeCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainHomeCategoryFragment : Fragment(R.layout.fragment_main_home_category) {

    private lateinit var binding: FragmentMainHomeCategoryBinding
    private lateinit var specialProductsAdapter: SpecialProductsAdapter
    private val viewModel by viewModels<MainHomeCategoryViewModel>()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHomeCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupSpecialProductsRv()
        lifecycleScope.launchWhenStarted {
            viewModel.specialProducts.collect {
                when (it) {
                    is Resource.Error -> {
                        hideLoading()
                        Toast.makeText(requireContext(), "Error data", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        showLoading()
                    }

                    is Resource.Success -> {
                        specialProductsAdapter.differ.submitList(it.data)
                        hideLoading()
                    }

                    else -> Unit
                }
            }
        }


    }

    private fun hideLoading() {
        binding.progressBarMainHomeCategory.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBarMainHomeCategory.visibility = View.VISIBLE
    }

    private fun setupSpecialProductsRv() {
        specialProductsAdapter = SpecialProductsAdapter()
        binding.rvSpecialProducts.apply {
            layoutManager = LinearLayoutManager(
                   requireContext(),
                   LinearLayoutManager.HORIZONTAL,
                   false
            )
            adapter = specialProductsAdapter
        }
    }
}
