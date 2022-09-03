package com.example.geizgames.data.remote

import com.example.geizgames.data.models.GameList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_TOKEN = "bdef87e302ac46b782ce7ec3f473df30"
const val BASE_URL = "https://api.rawg.io/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GameApiService {
    @GET("games?&key=$API_TOKEN")
    suspend fun getGames(): GameList

    @GET("games?&key=$API_TOKEN")
    suspend fun getResults(@Query("search") suchbegriff: String): GameList
}

object GameApi {
    val retrofitService: GameApiService by lazy { retrofit.create(GameApiService::class.java) }
}
