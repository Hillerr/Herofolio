package com.hiller.herofolio.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiller.herofolio.service.listener.APIListener
import com.hiller.herofolio.service.listener.ValidationListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.MarvelDataReponse
import com.hiller.herofolio.service.model.MarvelResponse
import com.hiller.herofolio.service.repository.CharacterRepository

class AllCharactersViewModel(application: Application) : AndroidViewModel(application) {
    private val mCharacterRepository = CharacterRepository(application)
    private val mList = MutableLiveData<List<CharacterResponse>>()
    private var mCalled: Boolean = false
    var characters: LiveData<List<CharacterResponse>> = mList

    private val mValidationListener = MutableLiveData<Boolean>()
    var mValidation: LiveData<Boolean> = mValidationListener

    fun getCharacters(){
        if(mCalled){
            mValidationListener.value = true
            return
        } else {
            mCharacterRepository.getCharacters(object : APIListener<MarvelResponse<MarvelDataReponse>> {
                override fun onFailure(str: String) {
                    mValidationListener.value = false
                }

                override fun onSuccess(model: MarvelResponse<MarvelDataReponse>) {
                    mCalled = true
                    mList.value = model.data?.characters
                }

            })
        }

    }


}