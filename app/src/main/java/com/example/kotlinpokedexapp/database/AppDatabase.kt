package com.example.kotlinpokedexapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinpokedexapp.database.dao.PokeDAO

abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokeDAO

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pokemon_app_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}