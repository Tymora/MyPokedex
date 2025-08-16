package tymora.myPokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.local.entity.RemoteKeys


@Database(
    entities = [PokemonBriefEntity::class, RemoteKeys::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}