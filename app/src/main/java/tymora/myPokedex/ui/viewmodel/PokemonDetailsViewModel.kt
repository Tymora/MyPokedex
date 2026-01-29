package tymora.myPokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import tymora.myPokedex.data.remote.model.pokemon.Pokemon
import tymora.myPokedex.domain.PokedexRepository

class PokemonDetailsViewModel(
    private val repository: PokedexRepository
) : ViewModel() {
    suspend fun detailPokemon(id: Int): Pokemon {
    Log.d("DETAILS", "VM detailPokemon id=$id")
    return repository.getPokemonDetails(id)
}}