package com.kundan.demoapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kundan.demoapp.databinding.ActivityMainBinding
import com.kundan.demoapp.model.ImageResponse
import com.kundan.demoapp.model.ImageResponseItem
import com.kundan.demoapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MyViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    val tempList= mutableListOf<ImageResponseItem>()
    companion object{
        var page=1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getImages(20, 1, "Desc")
        setUpRecyclerView()
        subscribeToObservables()
        showPagination()

    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter()
        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
    }

    private fun showPagination() {
        binding.rvImages.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(!recyclerView.canScrollVertically(1) && dy>0){
                   viewModel.getImages(10,++page,"Desc")
                }
            }
         }

        )
    }

    private fun subscribeToObservables() {
        viewModel.imageResponseLivedata.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    response.data?.let {
                        tempList.addAll(it)
                        imageAdapter.differ.submitList(tempList)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }
}