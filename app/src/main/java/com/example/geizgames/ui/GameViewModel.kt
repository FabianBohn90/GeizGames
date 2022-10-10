package com.example.geizgames.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.geizgames.data.AppRepository
import com.example.geizgames.data.models.Favorite
import com.example.geizgames.data.models.gameResults.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

const val TAG_VM = "GameViewModel"

enum class ApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val inputText = MutableLiveData<String>()
    var favorites = repository.getAllFavorites
    var slug = MutableLiveData<String>()
    var filterid: Int = 4
    val games = repository.gameData
    val games2 = repository.gameData2
    val shops = repository.shopData
    val images = repository.imageData

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    private var searchJob: Job? = null

    fun getGameListGenre(): LiveData<PagingData<Results>> {
        return repository.pagingDataGenre(filterid).cachedIn(viewModelScope)
    }

    fun getGameListPlatform(): LiveData<PagingData<Results>> {
        return repository.pagingDataPlatform(filterid).cachedIn(viewModelScope)
    }

    fun loadSearchData(suchbegriff: String) {
        viewModelScope.launch {
            searchJob?.cancelAndJoin()

            searchJob = this.launch {
                delay(400)
                repository.getResults(suchbegriff)
            }
        }
    }

    fun loadGenresData() {
        viewModelScope.launch {
            repository.getGenres()
        }
    }

    fun loadPlatformData() {
        viewModelScope.launch {
            repository.getPlatforms()
        }
    }

    fun loadImageData(gameName: String) {
        viewModelScope.launch {
            repository.getGameImage(gameName)
        }
    }

    fun loadShopData(gameName: String) {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getShops(gameName)
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG_VM, "Error loading ShopData from API: $e")
                _loading.value = ApiStatus.ERROR
            }
        }
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
            withContext(Dispatchers.Main) {}
        }
    }

    fun deleteFavoriteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteById(id)
        }
    }

    fun isFavorite(id: Int): Boolean {
        var favorite: Favorite?

        runBlocking(Dispatchers.IO) {
            favorite = repository.getFavoriteById(id)
        }

        if (favorite == null) return false
        return true
    }
}
