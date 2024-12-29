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
import com.example.e_comm.ui.vm.LoginViewModel
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
    }
}
