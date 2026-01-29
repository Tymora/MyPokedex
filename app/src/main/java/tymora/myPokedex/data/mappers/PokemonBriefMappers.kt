package tymora.myPokedex.data.mappers


import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.remote.model.PokemonBrief
import tymora.myPokedex.data.remote.model.PokemonNetwork


fun PokemonNetwork.toDomain(): PokemonBrief {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return PokemonBrief(id = id, name = name)
}

fun PokemonBrief.toEntity(position: Int): PokemonBriefEntity =
    PokemonBriefEntity(
        id = id,
        name = name,
        position = position
    )

fun PokemonBriefEntity.toDomain(): PokemonBrief =
    PokemonBrief(
        id = id,
        name = name
    )