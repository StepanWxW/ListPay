package com.stepan.listpay.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stepan.listpay.R
import com.stepan.listpay.data.repository.UserRepositoryImpl
import com.stepan.listpay.databinding.FragmentRegistrationBinding
import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.usecase.GetTokenUseCase
import com.stepan.listpay.presentation.exeption.ExceptionHandler
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val tokenUseCase = GetTokenUseCase(UserRepositoryImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonEnterClick()
    }

    private fun buttonEnterClick() {
        binding.buttonEnter.setOnClickListener {
            val login = binding.textLogin.text.toString()
            val password = binding.textPassword.text.toString()
            lifecycleScope.launch {
                try {
                    when (val result = tokenUseCase.getToken(login, password)) {
                        is ResponseResult.TokenResponse -> {
                            findNavController().navigate(R.id.action_registrationFragment_to_listFragment)
                        }
                        is ResponseResult.ErrorResponse -> {
                            val errorMsg = result.errorMsg
                            binding.messageError.text = errorMsg
                        }
                    }
                } catch (e: Exception) {
                    ExceptionHandler(requireContext()).handleException(e)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}