package tymora.myPokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tymora.myPokedex.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE label = :label")
    suspend fun get(label: String = "pokemon"): RemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(keys: RemoteKeys)

    @Query("DELETE FROM remote_keys WHERE label = :label")
    suspend fun delete(label: String = "pokemon")
}