package com.example.kotlinpokedexapp.database

import android.content.Context
import androidx.room.*
import com.example.kotlinpokedexapp.database.dao.PokeDAO
import com.example.kotlinpokedexapp.model.*

@Database(
    entities = [
        Pokemon::class,
        PokemonDetails::class,
        Sprites::class,
        Other::class,
        ArtWork::class,
        Types::class,
        PokemonType::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
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