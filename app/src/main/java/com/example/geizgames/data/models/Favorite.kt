package com.example.geizgames.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(

    @PrimaryKey
    val id: Int,
    val name: String,
    val imageLink: String = "",
    val metacritic: Int = 0,
    val slug: String,
    val release: String = "",
    val platform1: String = "",
    val platform2: String = "",
    val platform3: String = "",
    val platform4: String = "",
    val platform5: String = "",
    val platform6: String = "",
    val platform7: String = "",
    val platform8: String = "",
    val platform9: String = "",
    val platform10: String = "",
    val genre1: String = "",
    val genre2: String = "",
    val genre3: String = "",
    val genre4: String = "",
    val genre5: String = "",
    val genre6: String = "",
    val genre7: String = "",
    val genre8: String = "",
    val genre9: String = "",
    val genre10: String = ""
)
