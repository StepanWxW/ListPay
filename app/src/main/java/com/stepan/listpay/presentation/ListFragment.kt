package com.stepan.listpay.presentation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stepan.listpay.R
import com.stepan.listpay.data.ApiClient
import com.stepan.listpay.data.repository.PaymentRepositoryImpl
import com.stepan.listpay.data.repository.UserRepositoryImpl
import com.stepan.listpay.databinding.FragmentListBinding
import com.stepan.listpay.domain.usecase.GetPaymentsUseCase
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val paymentsUseCase = GetPaymentsUseCase(PaymentRepositoryImpl())

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
            val listPayments =
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
                    ApiClient.setToken("")
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