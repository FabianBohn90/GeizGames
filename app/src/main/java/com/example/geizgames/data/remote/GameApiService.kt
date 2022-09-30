package com.example.geizgames.data.remote

import com.example.geizgames.data.models.gameResults.ResultList
import com.example.geizgames.data.models.gameResults.ScreenShots
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_TOKEN = "bdef87e302ac46b782ce7ec3f473df30"
const val BASE_URL = "https://api.rawg.io/api/"

interface GameApiService {
    @GET("games/{gameName}/screenshots?&key=$API_TOKEN")
    suspend fun getGameID(@Path("gameName") gameName: String): ScreenShots

    @GET("games?&key=$API_TOKEN")
    suspend fun getGames(@Query("page") page: Int): ResultList

    @GET("games?&key=$API_TOKEN")
    suspend fun getGamesGenre(@Query("page") page: Int, @Query("genres") genre: Int): ResultList

    @GET("games?&key=$API_TOKEN")
    suspend fun getResults(@Query("search") suchbegriff: String): ResultList

//    @GET("games?&key=$API_TOKEN")
//    suspend fun getImages(): ResultList

    @GET("genres?&key=$API_TOKEN")
    suspend fun getGenres(): ResultList

    @GET("platforms?&key=$API_TOKEN")
    suspend fun getPlatforms(): ResultList
}
