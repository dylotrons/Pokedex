package com.example.kotlinpokedexapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinpokedexapp.model.Pokemon
import com.example.kotlinpokedexapp.model.Types

@Dao
interface PokeDAO {

    @Query("SELECT * FROM Pokemon")
    fun getAll(): List<Pokemon>?

    @Query("SELECT * FROM Pokemon WHERE poke_name = :pokeID")
    fun getById(pokeID: String): Pokemon?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(types: List<Types>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon WHERE poke_name LIKE '%' || :query || '%'")
    fun getFiltered(query: String): List<Pokemon>
}