//package com.kundan.demoapp.data
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.kundan.demoapp.model.ImageResponse
//import retrofit2.HttpException
//import java.io.IOException
//
//private const val STARTING_PAGE_INDEX = 1
//
class ImagesPagingSource {
    //(
//    private val service: ApiService
//) : PagingSource<Int, ImageResponse>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResponse> {
//        val pageIndex = params.key ?: STARTING_PAGE_INDEX
//        return try {
//            val response = service.getImage(
//                limit = 100,
//                page = pageIndex,
//                order = "Desc"
//            )
//            val images = response
//            val nextKey =
//                if (images.body()?.isEmpty() == true) {
//                    null
//                } else {
//                    pageIndex + (params.loadSize / 100)
//                }
//            LoadResult.Page(
//                data = null,
//                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
//                nextKey = nextKey
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, ImageResponse>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
}