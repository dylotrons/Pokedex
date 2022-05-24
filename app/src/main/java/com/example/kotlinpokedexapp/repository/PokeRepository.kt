package com.example.kotlinpokedexapp.repository

import android.content.Context
import com.example.kotlinpokedexapp.database.AppDatabase
import com.example.kotlinpokedexapp.model.Pokemon
import com.example.kotlinpokedexapp.model.PokemonDetails
import com.example.kotlinpokedexapp.services.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import kotlinx.coroutines.withContext

class PokeRepository(val context: Context) {

    private val database = AppDatabase.getDatabase(context)
    val service = RetrofitService.getPokeService()

    suspend fun getAll() : List<Pokemon>? {
        return withContext(
            CoroutineScope(Dispatchers.Default).coroutineContext
        ) {
            val response = service.getAllPoekomns()
            val responsePokemon = processData(response)

            responsePokemon?.results?.forEach {
                getPokemonDetail(
                    it.extractIdFromUrl()
                )?.let {
                        pokemonDetails ->
                    it.details = pokemonDetails
                }
            }
            responsePokemon?.results
        }
    }

    private suspend fun getPokemonDetail(pokeID: String) : PokemonDetails? {
        return withContext(
            CoroutineScope(Dispatchers.Default).coroutineContext
        ) {
            val response = service.getDetails(pokeID)
            processData(response)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

    fun insertInDatabase(pokemon: Pokemon) {
        val dao = database.pokemonDAO()
        dao.insert(pokemon)
    }

    fun getAllFromDatabase(): List<Pokemon>? {
        val dao = database.pokemonDAO()
        return dao.getAll()
    }

    fun getAllFromDatabaseWithFilter(query: String) : List<Pokemon> {
        val dao = database.pokemonDAO()
        return dao.getFiltered(query.lowercase())
    }

}