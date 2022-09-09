package com.example.geizgames.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.geizgames.data.AppRepository
import com.example.geizgames.data.models.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "GameViewModel"

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val inputText = MutableLiveData<String>()

    val games = repository.gameData
    private var searchJob: Job? = null

    fun getGameList(): LiveData<PagingData<Game>> {
        return repository.pagingData().cachedIn(viewModelScope)
    }

    fun loadSearchData(suchbegriff: String) {
        viewModelScope.launch {
            searchJob?.cancelAndJoin()
            searchJob = this.launch {
                delay(500)
                repository.getResults(suchbegriff)
            }
        }
    }
}
