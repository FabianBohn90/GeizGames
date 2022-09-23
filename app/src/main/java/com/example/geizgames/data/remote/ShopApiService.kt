package com.example.geizgames.data.remote

import com.example.geizgames.data.models.shopResults.ShopResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_TOKEN_SHOP = "4123b1338dmsha9f2246417802ffp1eea90jsn6254c4020e28"
const val BASE_URL_SHOP = "https://game-prices.p.rapidapi.com/game/"

interface ShopApiService {

    @GET("{gameName}")
    suspend fun getShops(
        @Path("gameName") gameName: String,
        @Query("region") region: String,
        @Query("type") type: String
    ): ShopResults
}
