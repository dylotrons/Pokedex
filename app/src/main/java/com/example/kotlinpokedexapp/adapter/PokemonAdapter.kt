package com.example.kotlinpokedexapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpokedexapp.R
import com.example.kotlinpokedexapp.databinding.ItemPokemonBinding
import com.example.kotlinpokedexapp.model.Pokemon
import com.example.kotlinpokedexapp.utils.toUpperFirstChar

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private var pokemons = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_pokemon,
            parent,
            false
        ).apply {
            return PokemonViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        pokemons[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<Pokemon>) {
        pokemons.clear()
        pokemons.addAll(newList)
        notifyDataSetChanged()
    }

}

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemPokemonBinding = ItemPokemonBinding.bind(itemView)

    @SuppressLint("SetTextI18n", "ResourceType", "UseCompatLoadingForDrawables")
    fun bind(pokemon: Pokemon) {

        binding.idTextView.text = "#${pokemon.extractIdFromUrl(withPads = true)}"
        binding.namePokemonTextView.text = pokemon.name.toUpperFirstChar()

        pokemon.details.let {
            Glide.with(itemView.context)
                .load(it.sprites.other.artWork?.image)
                .into(binding.avatarImageView)

            val pokeTypeSetup = it.type[0].type.extractPokeSetup()

            binding.cardItem.setCardBackgroundColor(
                itemView.context.getColor(pokeTypeSetup.colorCard)
            )

            binding.typesContainer.typeCardView.setCardBackgroundColor(
                itemView.context.getColor(pokeTypeSetup.colorType)
            )

            binding.typesContainer.typeImageView.setImageDrawable(
                itemView.context.getDrawable(pokeTypeSetup.icon)
            )

            binding.typesContainer.typeTextView.text = it.type[0].type.typeName.toUpperFirstChar()

            if (it.type.size > 1) {

                val setUpCard2 = it.type[1].type.extractPokeSetup()

                binding.typesContainer.typeCardView2.setCardBackgroundColor(
                    itemView.context.getColor(setUpCard2.colorType)
                )

                binding.typesContainer.typeImageView2.setImageDrawable(
                    itemView.context.getDrawable(setUpCard2.icon)
                )

                binding.typesContainer.typeTextView2.text = it.type[1].type.typeName.toUpperFirstChar()
                binding.typesContainer.typeCardView2.visibility = View.VISIBLE

            } else {
                binding.typesContainer.typeCardView2.visibility = View.GONE
            }

        }

    }

}