package com.example.geizgames.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geizgames.data.models.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): FavoriteDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, FavoriteDatabase::class.java, "FavoriteDatabase.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
