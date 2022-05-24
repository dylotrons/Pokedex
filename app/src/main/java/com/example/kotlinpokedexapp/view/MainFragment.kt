package com.example.kotlinpokedexapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpokedexapp.R
import com.example.kotlinpokedexapp.adapter.PokemonAdapter
import com.example.kotlinpokedexapp.databinding.FragmentMainBinding
import com.example.kotlinpokedexapp.model.Pokemon
import com.example.kotlinpokedexapp.viewModel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val adapter = PokemonAdapter()

    private val observerPokemon = Observer<List<Pokemon>?> {
        adapter.update(it)
    }

    private val observerIsLoading = Observer<Boolean> {
        binding.progressBar.visibility = if (it) VISIBLE else INVISIBLE
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pokemonRecyclerView.adapter = adapter

        viewModel.pokemons.observe(viewLifecycleOwner, observerPokemon)
        viewModel.isLoading.observe(viewLifecycleOwner, observerIsLoading)

        viewModel.getAllFromApi(requireContext())

        setButtonsClick()

    }

    private fun setButtonsClick() {
        binding.buttonSettings.setOnClickListener {
            println("TESTE!")
        }
    }


}