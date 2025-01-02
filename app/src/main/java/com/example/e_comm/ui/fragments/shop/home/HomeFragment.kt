package com.example.e_comm.ui.fragments.shop.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_comm.R
import com.example.e_comm.databinding.FragmentHomeBinding
import com.example.e_comm.databinding.FragmentLoginBinding
import com.example.e_comm.ui.fragments.shop.home.adapter.HomeViewPagerAdapter
import com.example.e_comm.ui.fragments.shop.home.category.AccessoryFragment
import com.example.e_comm.ui.fragments.shop.home.category.ChairFragment
import com.example.e_comm.ui.fragments.shop.home.category.CupboardFragment
import com.example.e_comm.ui.fragments.shop.home.category.FurnitureFragment
import com.example.e_comm.ui.fragments.shop.home.category.MainHomeCategoryFragment
import com.example.e_comm.ui.fragments.shop.home.category.TableFragment
import com.example.e_comm.ui.vm.LoginViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragments = arrayListOf<Fragment>(
               MainHomeCategoryFragment(),
               ChairFragment(),
               CupboardFragment(),
               TableFragment(),
               AccessoryFragment(),
               FurnitureFragment()
        )
        val viewPager2Adapter = HomeViewPagerAdapter(
               categoriesFragments,
               childFragmentManager,
               lifecycle
        )
        binding.viewpagerHome.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Главная"
                1 -> tab.text = "Стул"
                2 -> tab.text = "Шкаф"
                3 -> tab.text = "Стол"
                4 -> tab.text = "Аксессуар"
                5 -> tab.text = "Мебель"
            }
        }.attach()
    }
}
