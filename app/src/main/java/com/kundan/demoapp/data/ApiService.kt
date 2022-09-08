package com.kundan.demoapp.data

import com.kundan.demoapp.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/images/search?")
   suspend fun getImage(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<ImageResponse>
}