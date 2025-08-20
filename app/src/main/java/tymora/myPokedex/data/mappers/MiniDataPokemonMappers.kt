package tymora.myPokedex.data.mappers

import tymora.myPokedex.data.local.entity.MiniDataPokemonEntity
import tymora.myPokedex.data.remote.model.MiniDataPokemon


fun MiniDataPokemonEntity.toDomain() = MiniDataPokemon(
    id = id,
    name = name,
    sprites = sprites,
    types = types
)

fun MiniDataPokemon.toEntity() = MiniDataPokemonEntity(
    id = id,
    name = name,
    sprites = sprites,
    types = types
)