package com.stepan.listpay.presentation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.stepan.listpay.R
import com.stepan.listpay.data.ApiClient
import com.stepan.listpay.data.repository.PaymentRepositoryImpl
import com.stepan.listpay.databinding.FragmentListBinding
import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.usecase.GetPaymentsUseCase
import com.stepan.listpay.domain.usecase.LogoutUseCase
import com.stepan.listpay.presentation.adapter.PaymentAdapter
import com.stepan.listpay.presentation.exeption.ExceptionHandler
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val paymentsUseCase = GetPaymentsUseCase(PaymentRepositoryImpl())
    private val logoutUseCase = LogoutUseCase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            try {
                when (val result = paymentsUseCase.getPayments()) {
                    is ResponseResult.PaymentsResponse -> {
                        val adapter = PaymentAdapter(result.listPaymentItems)
                        binding.recyclerView.adapter = adapter
                        binding.messageError.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    is ResponseResult.ErrorResponse -> {
                        val errorMsg = result.errorMsg
                        binding.messageError.text = errorMsg
                        binding.messageError.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            } catch (e: Exception) {
                ExceptionHandler(requireContext()).handleException(e)
            }

            binding.progressBar.visibility = View.GONE
        }
        buttonExitClick()
    }

    private fun buttonExitClick() {
        binding.buttonExit.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.confirmation))
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.yes) { _, _ ->
                    findNavController().navigate(R.id.action_listFragment_to_registrationFragment)
                    logoutUseCase.logout()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}