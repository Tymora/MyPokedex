package tymora.myPokedex.data.mappers

import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.remote.model.PokemonBrief


fun PokemonBriefEntity.toDomain() = PokemonBrief(name = name, url = url)
fun PokemonBrief.toEntity() = PokemonBriefEntity(name, url)