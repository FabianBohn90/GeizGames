package com.example.geizgames.data.models.shopResults

data class ShopResults(
    val currency: String,
    val currentLowestPrice: Double,
    val id: String,
    val name: String,
    val stores: List<Stores>
)
