package com.hiller.herofolio.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fav_characters")
class FavoriteCharacter {
    @ColumnInfo(name="id") @PrimaryKey var id: Int = 0
    @ColumnInfo(name="name") var name = ""
    @ColumnInfo(name="favorite") var isFavorite: Boolean = false
    @ColumnInfo(name = "description") var description: String = ""
    @ColumnInfo(name="thumbnail") var thumbnail: String = ""
}