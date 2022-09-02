package com.example.geizgames.data.models

data class Game(
    var id: Long,
    val name: String,
    val background_image: String? = null,
    val released: String? = null,
    val metacritic: Int? = null,
    var platforms: List<Platforms>? = null
)
