package com.example.e_comm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_comm.R
import com.example.e_comm.databinding.FragmentIntrodcutionBinding
import com.example.e_comm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntrodcutionFragment : Fragment(R.layout.fragment_introdcution) {

    private lateinit var binding: FragmentIntrodcutionBinding
    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntrodcutionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRightAddressForShopping.setOnClickListener {
            findNavController().navigate(R.id.action_introdcutionFragment_to_accountOptionsFragment)
        }
    }


}