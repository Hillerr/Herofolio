package com.hiller.herofolio.service.repository.remote

import com.hiller.herofolio.service.model.MarvelDataReponse
import com.hiller.herofolio.service.model.MarvelResponse
import retrofit2.Call
import retrofit2.http.*

interface CharacterService {

    @GET("characters?limit=100")
    fun getCharacters(): Call<MarvelResponse<MarvelDataReponse>>

    @GET("characters/{id}")
    fun getCharacter(@Path("id") id: Int): Call<MarvelResponse<MarvelDataReponse>>
}