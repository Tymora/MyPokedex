package tymora.myPokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.local.entity.RemoteKeys
import tymora.myPokedex.data.repository.PokedexRepositoryImpl


@Database(
    entities = [PokemonBriefEntity::class, RemoteKeys::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}