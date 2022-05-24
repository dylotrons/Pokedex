package com.example.kotlinpokedexapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpokedexapp.model.Pokemon
import com.example.kotlinpokedexapp.repository.PokeRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>?>()
    val pokemons: LiveData<List<Pokemon>?> = _pokemons

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllFromApi(context: Context) {

        val repository = PokeRepository(context)

        _isLoading.value = true

        viewModelScope.launch {
            repository.getAll()?.let {
                _isLoading.value = false
                _pokemons.value = it
            }
        }

    }

    fun getAllFromLocalDataBase(context: Context) {
        val listOf = PokeRepository(context).getAllFromDatabase()

        if (listOf != null && listOf.isEmpty()) {
            _pokemons.value = listOf
        } else {
            getAllFromApi(context)
        }

    }

}