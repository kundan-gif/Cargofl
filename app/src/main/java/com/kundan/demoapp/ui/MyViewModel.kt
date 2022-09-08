package com.kundan.demoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundan.demoapp.data.Repository
import com.kundan.demoapp.model.ImageResponse
import com.kundan.demoapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _imageResponseLivedata: MutableLiveData<NetworkResult<ImageResponse>> =
        MutableLiveData()
    val imageResponseLivedata: LiveData<NetworkResult<ImageResponse>> = _imageResponseLivedata

    fun getImages(limit: Int, page: Int, order: String) = viewModelScope.launch {
       _imageResponseLivedata.value= NetworkResult.Loading()
        repository.getImage(limit, page, order)
            .collect {
                _imageResponseLivedata.value = it
            }
    }
}