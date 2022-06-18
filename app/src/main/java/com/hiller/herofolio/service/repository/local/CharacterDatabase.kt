package com.hiller.herofolio.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PriorityModel::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun priorityDAO():PriorityDAO

    companion object {
        private lateinit var INSTANCE: CharacterDatabase

        fun getDatabase(context: Context): CharacterDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(CharacterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, CharacterDatabase::class.java, "tasksDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}