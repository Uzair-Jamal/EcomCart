package com.example.ecomcart.fragments.loginRegister

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ecomcart.R
import com.example.ecomcart.data.User
import com.example.ecomcart.databinding.FragmentRegisterBinding
import com.example.ecomcart.util.RegisterValidation
import com.example.ecomcart.util.Resource
import com.example.ecomcart.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        binding.tvHaveAcc.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.apply {
            registerFragmentBtn.setOnClickListener {
                val user = User(
                    etFirstName.text.toString(),
                    etLastName.text.toString(),
                    etEmail.text.toString()
                )
                val password = etPassword.text.toString()
                viewModel.createUserWithEmailAndPassword(user,password)
            }
        }

        lifecycleScope.launchWhenCreated {
         viewModel.register.collect{
             when(it){
                 is Resource.Loading -> {
                     binding.registerFragmentBtn.startAnimation()
                 }
                 is Resource.Error -> {
                     Log.d("test",it.data.toString())
                     binding.registerFragmentBtn.revertAnimation()

                 }
                 is Resource.Success -> {
                    Log.e(TAG,it.message.toString())
                     binding.registerFragmentBtn.revertAnimation()
                 }
                 else -> Unit
             }
         }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.validation.collect{validation ->
                if(validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.etEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if(validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.etPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }

    }

}