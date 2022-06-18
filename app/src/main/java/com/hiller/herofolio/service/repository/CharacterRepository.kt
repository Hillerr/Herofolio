package com.hiller.herofolio.service.repository

import android.content.Context
import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import com.hiller.herofolio.service.listener.APIListener
import com.hiller.herofolio.service.repository.remote.RetrofitClient
import com.hiller.herofolio.service.repository.remote.CharacterService
import com.google.gson.Gson
import com.hiller.herofolio.service.model.MarvelDataReponse
import com.hiller.herofolio.service.model.MarvelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(CharacterService::class.java)

    fun getCharacters(listener: APIListener<MarvelResponse<MarvelDataReponse>>){
        val call: Call<MarvelResponse<MarvelDataReponse>> = mRemote.getCharacters()
        call.enqueue(object : Callback<MarvelResponse<MarvelDataReponse>> {
            override fun onResponse(
                call: Call<MarvelResponse<MarvelDataReponse>>,
                response: Response<MarvelResponse<MarvelDataReponse>>
            ) {
                if (response.code() != AppConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<MarvelResponse<MarvelDataReponse>>, t: Throwable) {
                listener.onFailure("Deu ruim")
            }

        })
    }

}


