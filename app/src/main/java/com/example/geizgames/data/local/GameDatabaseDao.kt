package com.example.geizgames.data.local

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.geizgames.data.models.Game

@Dao
interface GameDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(game: List<Game>)

    @Query("SELECT * FROM game_table")
    fun getAll(): LiveData<List<Game>>
}
