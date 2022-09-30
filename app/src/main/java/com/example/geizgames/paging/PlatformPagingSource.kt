package com.example.geizgames.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.geizgames.data.models.gameResults.Results
import com.example.geizgames.data.remote.GameApiService

class PlatformPagingSource(
    private val apiService: GameApiService,
    private val platform: Int
) : PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getGamesPlatform(page, platform)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else -1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
