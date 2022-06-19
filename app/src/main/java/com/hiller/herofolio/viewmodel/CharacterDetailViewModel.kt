package com.hiller.herofolio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiller.herofolio.service.listener.APIListener
import com.hiller.herofolio.service.listener.ValidationListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.MarvelDataReponse
import com.hiller.herofolio.service.model.MarvelResponse
import com.hiller.herofolio.service.repository.CharacterRepository

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mCharacterRepository = CharacterRepository(application)

    private val mValidation = MutableLiveData<Boolean>()
    var validationListener: LiveData<Boolean> = mValidation

    private val mCharacterModel = MutableLiveData<CharacterResponse>()
    var character: LiveData<CharacterResponse> = mCharacterModel

    fun getCharacter(id: Int){
        mCharacterRepository.getCharacter(id, object : APIListener<MarvelResponse<MarvelDataReponse>>{
            override fun onSuccess(model: MarvelResponse<MarvelDataReponse>) {
                mValidation.value = true
                mCharacterModel.value = model.data!!.characters.first()
            }

            override fun onFailure(str: String) {
                mValidation.value = false
            }

        })
    }

}