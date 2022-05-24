package com.example.kotlinpokedexapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinpokedexapp.R
import com.example.kotlinpokedexapp.adapter.PokemonAdapter
import com.example.kotlinpokedexapp.databinding.FragmentMainBinding
import com.example.kotlinpokedexapp.viewModel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val adapter = PokemonAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}