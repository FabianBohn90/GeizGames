package com.example.geizgames.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.geizgames.data.models.Game
import com.example.geizgames.data.remote.GameApiService
import com.example.geizgames.paging.GamePagingSource
import javax.inject.Inject

const val TAG = "AppRepository"

class AppRepository @Inject constructor(private val api: GameApiService) {

    private val _gameData = MutableLiveData<List<Game>>()
    val gameData: LiveData<List<Game>>
        get() = _gameData

    fun pagingData(): LiveData<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            GamePagingSource(api)
        }.liveData
    }

    suspend fun getResults(suchbegriff: String) {
        val result = api.getResults(suchbegriff)
        _gameData.value = result.results
    }
}
