package com.hiller.herofolio.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiller.herofolio.service.listener.APIListener
import com.hiller.herofolio.service.listener.ValidationListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.FavoriteCharacter
import com.hiller.herofolio.service.model.MarvelDataReponse
import com.hiller.herofolio.service.model.MarvelResponse
import com.hiller.herofolio.service.repository.CharacterRepository

class AllCharactersViewModel(application: Application) : AndroidViewModel(application) {
    private val mCharacterRepository = CharacterRepository(application)
    private val mList = MutableLiveData<List<CharacterResponse>>()
    var characters: LiveData<List<CharacterResponse>> = mList

    private val mSearchedCharacter = MutableLiveData<MutableList<CharacterResponse>>()
    var searchedCharacters: LiveData<MutableList<CharacterResponse>> = mSearchedCharacter

    private val mValidationListener = MutableLiveData<Boolean>()
    var mValidation: LiveData<Boolean> = mValidationListener

    private val mSearchListener = MutableLiveData<Boolean>()
    var searchListener: LiveData<Boolean> = mSearchListener

    private val mOrderListener = MutableLiveData<Boolean>(false)
    var orderListener: LiveData<Boolean> = mOrderListener

    fun getCharacters() {

        mCharacterRepository.getCharacters(object :
            APIListener<MarvelResponse<MarvelDataReponse>> {
            override fun onFailure(str: String) {
                mValidationListener.value = false
            }

            override fun onSuccess(model: MarvelResponse<MarvelDataReponse>) {
                mValidationListener.value = true
                var characters = verifyFavorites(model.data!!.characters)
                mList.value = characters

            }

        })
    }

    fun filterCharactersByName(name: String) {
        var found = mutableListOf<CharacterResponse>()
        mSearchedCharacter.value?.clear()
        val pattern = name.lowercase().toRegex()

        for(item in mList.value!!){
            if(pattern.containsMatchIn(item.name.lowercase())){
                found.add(item)
            }
        }

        mSearchedCharacter.value = found
        mSearchListener.value = true
    }

    fun clearSearch(){
        mSearchListener.value = false
    }

    private fun verifyFavorites(characters: List<CharacterResponse>): List<CharacterResponse> {
        val fav = mCharacterRepository.listFavoriteCharacters()
        val ids = mutableListOf<Int>()
        for (item in fav) {
            ids.add(item.id)
        }
        characters!!.forEach { favoriteCharacter ->
            if (favoriteCharacter.id in ids) {
                favoriteCharacter.isFavorite = true
            }
        }
        return characters
    }

    fun favoriteCharacter(character: CharacterResponse) {
        character.isFavorite = !character.isFavorite

        if (character.isFavorite) {
            var favCharacter = FavoriteCharacter()
            favCharacter.id = character.id
            favCharacter.name = character.name
            favCharacter.description = character.description
            favCharacter.isFavorite = character.isFavorite
            favCharacter.thumbnail = "${character.thumbnail.path}.${character.thumbnail.extension}"

            mCharacterRepository.saveFavoriteCharacter(favCharacter)
        } else {
            mCharacterRepository.deleteFavoriteCharacter(character.id)
        }

    }

    fun orderCharacters() {
        mList.value = mList.value?.reversed()
        mOrderListener.value = !mOrderListener.value!!
    }
}