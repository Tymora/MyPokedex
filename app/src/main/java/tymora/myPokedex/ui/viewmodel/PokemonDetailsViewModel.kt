package tymora.myPokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import tymora.myPokedex.data.remote.PokedexApi
import tymora.myPokedex.data.remote.model.pokemon.Pokemon

class PokemonDetailsViewModel(
    private val api: PokedexApi
) : ViewModel() {
    suspend fun detailPokemon(name: String?): Pokemon = api.getPokemon(name)
}