package tymora.myPokedex.data.mappers


import tymora.myPokedex.data.local.entity.PokemonEntity
import tymora.myPokedex.data.remote.model.pokemon.Pokemon

fun Pokemon.toEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types
    )


fun PokemonEntity.toDomain() =
    Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types,
        abilities = emptyList(),
    )


