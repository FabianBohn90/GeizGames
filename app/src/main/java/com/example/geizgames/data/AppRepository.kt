package com.example.geizgames.data

import androidx.lifecycle.LiveData
import com.example.geizgames.data.local.GameDatabase
import com.example.geizgames.data.models.Game
import com.example.geizgames.data.remote.GameApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "AppRepository"

class AppRepository(private val api: GameApi, private val database: GameDatabase) {

    val games: LiveData<List<Game>> = database.gameDatabaseDao.getAll()

    suspend fun getGames() {
        withContext(Dispatchers.IO) {
            val gameData = api.retrofitService.getGames().results
            database.gameDatabaseDao.insertAll(gameData)
        }
    }
}
