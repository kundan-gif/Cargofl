package com.kundan.demoapp.data

import com.kundan.demoapp.model.ImageResponse
import com.kundan.demoapp.utils.BaseApiResponse
import com.kundan.demoapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getImage(limit: Int, page: Int, order: String): Flow<NetworkResult<ImageResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getImage(limit, page, order) })
        }
    }
}