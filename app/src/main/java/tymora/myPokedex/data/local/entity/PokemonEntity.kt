package tymora.myPokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import tymora.myPokedex.data.remote.model.pokemon.Type


@Entity(tableName = "pokemon_details")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
)
