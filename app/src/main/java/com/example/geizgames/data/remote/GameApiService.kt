package com.example.geizgames.data.remote

import com.example.geizgames.data.models.GameList
import retrofit2.http.GET
import retrofit2.http.Query

const val API_TOKEN = "bdef87e302ac46b782ce7ec3f473df30"
const val BASE_URL = "https://api.rawg.io/api/"

interface GameApiService {
    @GET("games?&key=$API_TOKEN")
    suspend fun getGames(@Query("page") page: Int): GameList

    @GET("games?&key=$API_TOKEN")
    suspend fun getResults(@Query("search") suchbegriff: String): GameList
}
