package com.hiller.herofolio.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hiller.herofolio.service.model.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun favCharacterDAO():FavoriteCharacterDAO

    companion object {
        private lateinit var INSTANCE: CharacterDatabase

        fun getDatabase(context: Context): CharacterDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(CharacterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, CharacterDatabase::class.java, "fav_characters")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}