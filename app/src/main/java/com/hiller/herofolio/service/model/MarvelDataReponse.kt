package com.hiller.herofolio.service.model

import com.google.gson.annotations.SerializedName

class MarvelDataReponse(
    @SerializedName("results") val characters: List<CharacterResponse>,
    val offset: Int,
    val total: Int,
)