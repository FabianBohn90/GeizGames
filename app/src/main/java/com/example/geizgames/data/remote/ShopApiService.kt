package com.example.geizgames.data.remote

import com.example.geizgames.data.models.shopResults.ShopResults
import retrofit2.http.GET
import retrofit2.http.Path

const val API_TOKEN_SHOP = "6fd287978cmshb3da3b00cdc65e8p122e81jsnc82726b23080"
const val BASE_URL_SHOP = "https://game-prices.p.rapidapi.com/game/"

interface ShopApiService {

    @GET("{gameName}?region=de&type=game")
    suspend fun getShops(
        @Path("gameName") gameName: String
    ): ShopResults
}
