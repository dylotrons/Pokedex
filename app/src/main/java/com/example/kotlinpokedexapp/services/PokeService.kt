package com.example.kotlinpokedexapp.services

import com.example.kotlinpokedexapp.model.PokeResponse
import com.example.kotlinpokedexapp.model.PokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("/api/v2/pokemon?limit=200")
    suspend fun getAllPoekomns(): Response<PokeResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<PokemonDetails>

}