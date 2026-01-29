package tymora.myPokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tymora.myPokedex.data.local.entity.PokemonEntity


@Dao
interface PokemonDetailsDao {
    @Query("SELECT * FROM pokemon_details WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PokemonEntity)

}