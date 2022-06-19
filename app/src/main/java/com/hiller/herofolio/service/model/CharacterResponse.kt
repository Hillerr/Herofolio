package com.hiller.herofolio.service.model

class CharacterResponse(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnail: ThumbnailResponse = ThumbnailResponse("", ""),
    val comics: ComicsResponse = ComicsResponse(0),
    val urls: List<UrlResponse> = listOf(UrlResponse("", "")),
    var isFavorite: Boolean = false
)