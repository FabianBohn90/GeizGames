package com.example.geizgames.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.geizgames.data.models.gameResults.Results
import com.example.geizgames.data.models.gameResults.Screens
import com.example.geizgames.data.models.shopResults.Stores
import com.example.geizgames.data.remote.GameApiService
import com.example.geizgames.data.remote.ShopApiService
import com.example.geizgames.paging.GamePagingSource
import com.example.geizgames.paging.GeneresPagingSource
import javax.inject.Inject

const val TAG_REPO = "AppRepository"

class AppRepository @Inject constructor(
    private val api: GameApiService,
    private val apiShop: ShopApiService
) {

    private val _gameData = MutableLiveData<List<Results>>()
    val gameData: LiveData<List<Results>>
        get() = _gameData

    private val _shopData = MutableLiveData<List<Stores>>()
    val shopData: LiveData<List<Stores>>
        get() = _shopData

    private val _imageData = MutableLiveData<List<Screens>>()
    val imageData: MutableLiveData<List<Screens>>
        get() = _imageData

    suspend fun getGameImage(gameName: String) {
        try {
            val images = api.getGameID(gameName)
            _imageData.value = images.results
        } catch (e: Exception) {
            Log.e(TAG_REPO, "Error Loading ImageData from Api $e")
        }
    }

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
        try {
            val result = api.getResults(suchbegriff)
            _gameData.value = result.results
        } catch (e: Exception) {
            Log.e(TAG_REPO, "Error Loading ResultsData from Api $e")
        }
    }

    suspend fun getGenres() {
        try {
            val result = api.getGenres()
            _gameData.value = result.results
        } catch (e: Exception) {
            Log.e(TAG_REPO, "Error Loading GenreData from Api $e")
        }
    }

    suspend fun getShops(gameName: String) {
        try {
            val shopResults = apiShop.getShops(gameName)
            _shopData.value = shopResults.stores
        } catch (e: Exception) {
            Log.e(TAG_REPO, "Error Loading ShopData from Api $e")
        }
    }
}
