package com.kundan.demoapp.data

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getImage(limit: Int, page: Int, order: String) =
        apiService.getImage(limit = limit, page = page, order = order)
}