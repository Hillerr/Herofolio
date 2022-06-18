package com.hiller.herofolio.service.listener

interface CharacterListener {

    /**
     * Click para visualizar detalhes
     */
    fun onDetailClick(id: Int)

    /**
     * Click para favoritar
     */
    fun onAddFavoriteClick(id: Int)

    /**
     * Click para desfavoritar
     */
    fun onRemoveFavorite(id: Int)

}