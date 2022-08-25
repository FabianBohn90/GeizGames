package com.example.geizgames.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    val name: String,
    val background_image: String,
    val released: String,
    val metacritic: Int
)
