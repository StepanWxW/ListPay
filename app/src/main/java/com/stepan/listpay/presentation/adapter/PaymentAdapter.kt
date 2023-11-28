package com.stepan.listpay.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stepan.listpay.databinding.ItemPaymentBinding
import com.stepan.listpay.domain.model.PaymentItem


class PaymentAdapter(private val paymentItems: List<PaymentItem>) :
    RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val paymentItem = paymentItems[position]
        holder.bind(paymentItem)
    }

    override fun getItemCount(): Int {
        return paymentItems.size
    }

    inner class PaymentViewHolder(private val binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentItem: PaymentItem) {
            binding.apply {
                textId.text = paymentItem.id.toString()
                textTitle.text = paymentItem.title
                textAmount.text = paymentItem.amount?.toString() ?: "N/A"
                textCreated.text = paymentItem.created.toString()
            }
        }
    }
}