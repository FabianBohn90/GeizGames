package com.example.geizgames.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.geizgames.data.AppRepository
import com.example.geizgames.data.remote.GameApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG = "GameViewModel"

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val inputText = MutableLiveData<String>()

    private val repository = AppRepository(GameApi)

    val games = repository.gameData
    private var searchJob: Job? = null

    fun loadData() {
        viewModelScope.launch {
            try {
                repository.getGames()
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Data $e")
            }
        }
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
