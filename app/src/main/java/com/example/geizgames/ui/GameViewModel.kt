package com.example.geizgames.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.geizgames.data.AppRepository
import com.example.geizgames.data.models.gameResults.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG_VM = "GameViewModel"

enum class ApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val inputText = MutableLiveData<String>()

    var slug = MutableLiveData<String>()
    var genres: Int = 4
    val games = repository.gameData
    val shops = repository.shopData
    val images = repository.imageData

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    private var searchJob: Job? = null

    fun getGameList(): LiveData<PagingData<Results>> {
        return repository.pagingData().cachedIn(viewModelScope)
    }

    fun getGameListGenre(): LiveData<PagingData<Results>> {
        return repository.pagingDataGenre(genres).cachedIn(viewModelScope)
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
}
