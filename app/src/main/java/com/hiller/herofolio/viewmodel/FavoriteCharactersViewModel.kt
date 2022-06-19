package com.hiller.herofolio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.FavoriteCharacter
import com.hiller.herofolio.service.repository.CharacterRepository

class FavoriteCharactersViewModel(application: Application) : AndroidViewModel(application) {
    private val mCharacterRepository = CharacterRepository(application)
    private val mList = MutableLiveData<List<FavoriteCharacter>>()
    var characters: LiveData<List<FavoriteCharacter>> = mList
    private val mIsEmpty = MutableLiveData<Boolean>()
    var isEmpty: LiveData<Boolean> = mIsEmpty

    /**
     * Obtem personagens salvos no banco
     */
    fun getCharacters(){
        val list = mCharacterRepository.listFavoriteCharacters()
        mIsEmpty.value = list.isEmpty()
        mList.value = list
    }

    /**
     * Remove personagem salvo no banco e atualiza a lista
     */
    fun deleteFavoriteCharacter(id: Int){
        mCharacterRepository.deleteFavoriteCharacter(id)
        getCharacters()

    }
}