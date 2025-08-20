package tymora.myPokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import tymora.myPokedex.data.remote.model.pokemon.Sprites
import tymora.myPokedex.data.remote.model.pokemon.Type

@Entity(tableName = "mini_data_pokemon")
data class MiniDataPokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
)