package tymora.myPokedex.data.remote.model

import tymora.myPokedex.data.remote.model.pokemon.Sprites
import tymora.myPokedex.data.remote.model.pokemon.Type

data class MiniDataPokemon(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
)
