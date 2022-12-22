package com.assignment.currenciesrate.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.assignment.currenciesrate.ui.viewmodel.CurrencyListViewModel
import com.example.currenciesrate.databinding.ActivityMainBinding

class CurrencyListActivity : AppCompatActivity() {
    lateinit var viewModel: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CurrencyListViewModel::class.java]
        viewModel.getCurrencyList()
        viewModel.currencyListLiveData.observe(this) {
            binding.rv.adapter = RvCurrencyListAdapter(it)
        }

        viewModel.loading.observe(this){
            binding.progressBar.visibility = View.GONE
        }
    }
}