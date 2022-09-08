package com.kundan.demoapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kundan.demoapp.databinding.ActivityMainBinding
import com.kundan.demoapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MyViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getImages(20, 1, "Desc")
        setUpRecyclerView()
        subscribeToObservables()

    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter()
        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
    }


    private fun subscribeToObservables() {
        viewModel.imageResponseLivedata.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressCircular.visibility=View.GONE
                    response.data?.let {
                        imageAdapter.differ.submitList(it)
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressCircular.visibility=View.GONE
                    Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                     binding.progressCircular.visibility=View.VISIBLE
                }
            }
        }
    }
}