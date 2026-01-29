package tymora.myPokedex.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tymora.myPokedex.data.remote.model.PokemonBrief
import tymora.myPokedex.data.remote.model.pokemon.Pokemon

interface PokedexRepository {

    fun getPokemonPaged(): Flow<PagingData<PokemonBrief>>
    suspend fun getPokemonDetails(id: Int): Pokemon
}