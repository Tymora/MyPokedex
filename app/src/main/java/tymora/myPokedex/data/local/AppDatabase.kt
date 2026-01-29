package tymora.myPokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.local.entity.PokemonEntity


@Database(
    entities = [PokemonBriefEntity::class, PokemonEntity::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailsDao(): PokemonDetailsDao
}