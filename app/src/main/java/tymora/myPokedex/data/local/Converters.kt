package tymora.myPokedex.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tymora.myPokedex.data.remote.model.pokemon.Sprites
import tymora.myPokedex.data.remote.model.pokemon.Type

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromSprites(value: Sprites): String = gson.toJson(value)

    @TypeConverter
    fun toSprites(value: String): Sprites = gson.fromJson(value, Sprites::class.java)

    @TypeConverter
    fun fromTypes(types: List<Type>): String = gson.toJson(types)

    @TypeConverter
    fun toTypes(value: String): List<Type> {
        val listType = object : TypeToken<List<Type>>() {}.type
        return gson.fromJson(value, listType)
    }
}