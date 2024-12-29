package com.example.e_comm.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_comm.R
import com.example.e_comm.databinding.FragmentIntrodcutionBinding
import com.example.e_comm.ui.ShoppingActivity
import com.example.e_comm.ui.vm.IntroductionViewModel
import com.example.e_comm.ui.vm.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENTS
import com.example.e_comm.ui.vm.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntrodcutionFragment : Fragment(R.layout.fragment_introdcution) {

    private lateinit var binding: FragmentIntrodcutionBinding
    private val viewModel by viewModels<IntroductionViewModel>()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntrodcutionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRightAddressForShopping.setOnClickListener {
            viewModel.startBtnClick()
            findNavController().navigate(R.id.action_introdcutionFragment_to_accountOptionsFragment)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.navigate.collect {
                when (it) {
                    SHOPPING_ACTIVITY -> {
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }

                    ACCOUNT_OPTIONS_FRAGMENTS -> {
                        findNavController().navigate(R.id.action_introdcutionFragment_to_accountOptionsFragment)
                    }

                    else -> Unit
                }
            }
        }
    }
}
