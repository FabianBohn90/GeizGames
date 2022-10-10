package com.example.geizgames.data.models.gameResults

data class Results(
    var id: Int,
    val name: String,
    val slug: String,
    val background_image: String?,
    val image_background: String?,
    val released: String?,
    val metacritic: Int?,
    var platforms: List<Platforms>?,
    val genres: List<Genres>?,
    val games: List<Games>?
)
