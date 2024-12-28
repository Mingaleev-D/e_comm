package com.example.e_comm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_comm.R
import com.example.e_comm.data.model.User
import com.example.e_comm.data.utils.Resource
import com.example.e_comm.databinding.FragmentRegisterBinding
import com.example.e_comm.ui.vm.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val TAG = "RegisterFragment"

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegister.setOnClickListener {
                val user = User(
                       firstName = etNameRegister.text.toString().trim(),
                       lastName = etSurnameRegister.text.toString().trim(),
                       email = etEmailRegister.text.toString().trim(),

                       )
                val passwordStr = etPasswordRegister.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user, passwordStr)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBarRegister.visibility = View.GONE
                        Log.d(TAG, "onViewCreated: ${it.data}")
                    }

                    is Resource.Error -> {
                        binding.progressBarRegister.visibility = View.GONE
                        Log.d(TAG, "onViewCreated error: ${it.message.toString()}")
                    }

                    is Resource.Loading -> {
                        binding.progressBarRegister.visibility = View.VISIBLE

                    }

                    else -> Unit
                }
            }
        }
    }


}