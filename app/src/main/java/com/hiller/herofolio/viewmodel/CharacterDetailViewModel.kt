package com.hiller.herofolio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiller.herofolio.service.listener.ValidationListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.repository.CharacterRepository

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mCharacterRepository = CharacterRepository(application)

    private val mValidation = MutableLiveData<ValidationListener>()
    var validationListener: LiveData<ValidationListener> = mValidation

    private val mCharacterModel = MutableLiveData<CharacterResponse>()
    var character: LiveData<CharacterResponse> = mCharacterModel

    fun getCharacters(){
        
    }

}