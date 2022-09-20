package com.example.geizgames.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.geizgames.data.models.Results
import com.example.geizgames.data.remote.GameApiService
import com.example.geizgames.paging.GamePagingSource
import com.example.geizgames.paging.GeneresPagingSource
import javax.inject.Inject

const val TAG = "AppRepository"

class AppRepository @Inject constructor(
    private val api: GameApiService
) {

    private val _gameData = MutableLiveData<List<Results>>()
    val gameData: LiveData<List<Results>>
        get() = _gameData

    fun pagingData(): LiveData<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            GamePagingSource(api)
        }.liveData
    }

    fun pagingDataGenre(genre: Int): LiveData<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            GeneresPagingSource(api, genre)
        }.liveData
    }

    suspend fun getResults(suchbegriff: String) {
        val result = api.getResults(suchbegriff)
        _gameData.value = result.results
    }

    suspend fun getGenres() {
        val result = api.getGenres()
        _gameData.value = result.results
    }
}
