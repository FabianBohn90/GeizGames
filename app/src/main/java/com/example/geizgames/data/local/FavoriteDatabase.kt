package com.example.geizgames.data.local // ktlint-disable filename

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geizgames.data.models.Favorite

@Database(
    entities = [Favorite::class],
    version = 1
)

abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getDao(): FavoriteDao
}
