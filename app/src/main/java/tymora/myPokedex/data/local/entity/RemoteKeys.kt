package tymora.myPokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val label: String = "pokemon",
    val nextOffset: Int?,
    val prevOffset: Int? = null,
    val lastUpdated: Long? = null
)