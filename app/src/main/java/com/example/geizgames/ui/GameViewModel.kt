package com.example.geizgames.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.geizgames.data.AppRepository
import com.example.geizgames.data.remote.GameApi
import kotlinx.coroutines.launch

const val TAG = "GameViewModel"

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(GameApi)

    val games = repository.gameData

    fun loadData() {
        viewModelScope.launch {
            try {
                repository.getGames()
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Data $e")
            }
        }
    }
}
