package com.example.geizgames.data.models.shopResults

data class ShopResults(
    val currency: String,
    val currentLowestPrice: Long,
    val id: String,
    val name: String,
    val stores: List<Stores>
)
