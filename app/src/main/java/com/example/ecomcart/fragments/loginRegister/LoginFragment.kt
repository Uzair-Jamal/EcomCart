package com.example.ecomcart.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ecomcart.R
import com.example.ecomcart.activities.ShoppingActivity
import com.example.ecomcart.databinding.FragmentLoginBinding
import com.example.ecomcart.dialog.setupBottomSheetDialog
import com.example.ecomcart.util.Resource
import com.example.ecomcart.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.tvForgetPass.setOnClickListener {
                setupBottomSheetDialog { email ->
                    viewModel.resetPassword(email)

                }
            }

        lifecycleScope.launchWhenCreated {
            viewModel.resetPassword.collect{
                when(it){
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Snackbar.make(requireView(),"Reset email successfully sent!", Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(),"Error ${it.message}", Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

            binding.tvHaveAcc.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            binding.apply {
                loginBtnFragment.setOnClickListener {
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString()
                    viewModel.login(email,password)
                }
            }

        lifecycleScope.launchWhenCreated {
            viewModel.login.collect{
                when(it){
                    is Resource.Loading ->{
                        binding.loginBtnFragment.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.loginBtnFragment.revertAnimation()
                        Intent(requireActivity(),ShoppingActivity::class.java).also{intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                        binding.loginBtnFragment.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }

    }
}