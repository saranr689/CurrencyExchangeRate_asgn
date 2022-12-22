package com.assignment.currenciesrate.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.currenciesrate.model.CurrencyCustomModelList
import com.example.currenciesrate.databinding.CurrencyItemListBinding
import java.util.*


class RvCurrencyListAdapter(val currencyCustomModelLists: LinkedList<CurrencyCustomModelList>) :
    RecyclerView.Adapter<RvCurrencyListAdapter.ViewHolder>() {
    class ViewHolder(val binding: CurrencyItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CurrencyItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencyCustomModelLists.size - 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currencyTv.text = currencyCustomModelLists[position].currencyCode.uppercase()
        holder.binding.currencyRateTv.text =
            currencyCustomModelLists[position].currencyValue.toString()
    }
}