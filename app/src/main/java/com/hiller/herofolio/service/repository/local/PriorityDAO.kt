package com.hiller.herofolio.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hiller.herofolio.service.model.FavoriteCharacter

@Dao
interface PriorityDAO {

    @Insert
    fun save(list: List<FavoriteCharacter>)

    @Query("DELETE FROM fav_characters")
    fun clean()

    @Query("SELECT description FROM fav_characters WHERE id = :id")
    fun getDescription(id: Int): String

    @Query("SELECT * FROM fav_characters")
    fun list(): List<FavoriteCharacter>
}