package com.example.geizgames.data.models

data class Game(
    var id: Long,
    val name: String,
    val background_image: String?,
    val released: String?,
    val metacritic: Int?,
    var platforms: List<Platforms>?,
    val genres: List<Genre>,
    val short_screenshots: List<ScreenShots>,
    val tags: List<Tags>
)
