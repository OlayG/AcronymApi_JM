package com.example.acronymapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronymapi.adapter.AcronymAdapter
import com.example.acronymapi.databinding.ActivityMainBinding
import com.example.acronymapi.util.Resource
import com.example.acronymapi.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : AcronymViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.toString().isNotBlank()) {
                viewModel.fetchAcronyms(binding.etSearch.text.toString())
            }
        }

        viewModel.acronym.observe(this) { result ->

            binding.apply {
                val data = if(result.data?.isNotEmpty() == true) result.data[0].lfs else listOf()

                rvAcronymList.apply {
                    adapter = AcronymAdapter(data)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                tvError.text = result.error
            }
        }

    }
}