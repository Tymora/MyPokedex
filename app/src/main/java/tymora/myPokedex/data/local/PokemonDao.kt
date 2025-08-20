package tymora.myPokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tymora.myPokedex.data.local.entity.PokemonBriefEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_brief ORDER BY name ASC")
    fun pagingSource(): PagingSource<Int, PokemonBriefEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PokemonBriefEntity>)

    @Query("SELECT MAX(position) FROM pokemon_brief")
    suspend fun lastPosition(): Int?

    @Query("DELETE FROM pokemon_brief")
    suspend fun clearAll()
}