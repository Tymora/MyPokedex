package tymora.myPokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tymora.myPokedex.data.local.entity.MiniDataPokemonEntity


@Dao
interface MiniDataDao {
    @Query("SELECT * FROM mini_data_pokemon ORDER BY name ASC")
    fun getMiniData(): MiniDataPokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<MiniDataPokemonEntity>)

    @Query("DELETE FROM pokemon_brief")
    suspend fun clearAll()
}