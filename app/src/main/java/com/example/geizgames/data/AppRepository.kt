package com.example.geizgames.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geizgames.data.models.Game
import com.example.geizgames.data.remote.GameApi

const val TAG = "AppRepository"

class AppRepository(private val api: GameApi) {

    private val _gameData = MutableLiveData<List<Game>>()
    val gameData: LiveData<List<Game>>
        get() = _gameData

    suspend fun getGames() {
        val gameList = api.retrofitService.getGames().results
        _gameData.value = gameList
    }
    suspend fun getResults(suchbegriff: String) {
        val result = api.retrofitService.getResults(suchbegriff)
        _gameData.value = result.results
    }
}
