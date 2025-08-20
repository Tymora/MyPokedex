package tymora.myPokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tymora.myPokedex.data.local.entity.MiniDataPokemonEntity


@Dao
interface MiniDataDao {
    @Query("SELECT * FROM mini_data_pokemon WHERE name = :name LIMIT 1")
    suspend fun getMiniData(name: String): MiniDataPokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: MiniDataPokemonEntity)

    @Query("DELETE FROM mini_data_pokemon")
    suspend fun clearAll()
}