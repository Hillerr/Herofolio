package com.hiller.herofolio.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.FavoriteCharacter

@Dao
interface FavoriteCharacterDAO {

    @Insert
    fun save(character: FavoriteCharacter)

    @Query("DELETE FROM fav_characters")
    fun clean()

    @Query("SELECT * FROM fav_characters WHERE id = :id")
    fun getCharacter(id: Int): FavoriteCharacter

    @Query("SELECT * FROM fav_characters")
    fun list(): List<FavoriteCharacter>

    @Query("DELETE FROM fav_characters WHERE id = :id")
    fun delete(id: Int)
}