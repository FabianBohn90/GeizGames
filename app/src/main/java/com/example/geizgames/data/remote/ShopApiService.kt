package com.example.geizgames.data.remote

import com.example.geizgames.data.models.shopResults.ShopResults
import retrofit2.http.GET
import retrofit2.http.Path

const val API_TOKEN_SHOP = "e0b23cd9a2msh93ed9ac4619764bp10fc17jsn0ad8f1cbac89"
const val BASE_URL_SHOP = "https://game-prices.p.rapidapi.com/game/"

interface ShopApiService {

    @GET("{gameName}?region=de&type=game")
    suspend fun getShops(
        @Path("gameName") gameName: String
    ): ShopResults
}
