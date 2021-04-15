package com.example.acronymapi.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.acronymapi.R
import com.example.acronymapi.adapter.AcronymAdapter
import com.example.acronymapi.databinding.ActivityMainBinding
import com.example.acronymapi.util.Resource
import com.example.acronymapi.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val acronymAdapter: AcronymAdapter by lazy { AcronymAdapter() }
    private val acronymViewModel: AcronymViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupObservers()
    }

    private fun setupDataBinding() {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
            viewModel = acronymViewModel
            adapter = acronymAdapter
        }
    }

    private fun setupObservers() = with(acronymViewModel) {
        acronym.observe(this@MainActivity) {
            if (it is Resource.Success && it.data != null) acronymAdapter.loadData(it.data)
        }
    }
}
