package com.hiller.herofolio.service.listener

import com.hiller.herofolio.service.model.CharacterResponse

interface CharacterListener {

    /**
     * Click para visualizar detalhes
     */
    fun onDetailClick(id: Int, name: String, description: String, thumbnail: String)

    /**
     * Click para favoritar
     */
    fun onFavoriteClick(character: CharacterResponse)

    /**
     * Click para desfavoritar
     */
    fun onRemoveFavorite(id: Int)

    /**
     * Click para selecionar a ordem
     */
    fun onOrderClick()

}