package com.example.geizgames.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.geizgames.data.models.Favorite

@Dao
interface FavoriteDao {

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    fun deleteFavoriteById(id: Int)

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getFavoriteById(id: Int): Favorite

    @Query("SELECT * FROM favorite_table order by name ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>
}
