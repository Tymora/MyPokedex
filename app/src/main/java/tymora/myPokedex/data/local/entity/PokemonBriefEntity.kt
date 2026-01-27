package tymora.myPokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_brief")
data class PokemonBriefEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String,
    val position: Int
)
