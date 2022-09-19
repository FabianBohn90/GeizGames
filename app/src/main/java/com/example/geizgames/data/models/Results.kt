package com.example.geizgames.data.models

data class Results(
    var id: Long,
    val name: String,
    val background_image: String?,
    val image_background: String?,
    val released: String?,
    val metacritic: Int?,
    var platforms: List<Platforms>?,
    val short_screenshots: List<ScreenShots>?,
    val tags: List<Tags>?,
    val games: List<Games>?
)
