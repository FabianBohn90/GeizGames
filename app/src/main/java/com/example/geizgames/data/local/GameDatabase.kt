package com.example.geizgames.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geizgames.data.models.Game

@Database(entities = [Game::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract val gameDatabaseDao: GameDatabaseDao
}

private lateinit var INSTANCE: GameDatabase

// if there's no Database a new one is built
fun getDatabase(context: Context): GameDatabase {
    synchronized(GameDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                "game_table"
            )
                .build()
        }
    }
    return INSTANCE
}
