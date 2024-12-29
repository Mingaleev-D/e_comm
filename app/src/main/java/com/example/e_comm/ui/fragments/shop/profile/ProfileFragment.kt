package com.example.e_comm.ui.fragments.shop.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_comm.R
import com.example.e_comm.databinding.FragmentHomeBinding
import com.example.e_comm.databinding.FragmentProfileBinding
import com.example.e_comm.ui.fragments.shop.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
