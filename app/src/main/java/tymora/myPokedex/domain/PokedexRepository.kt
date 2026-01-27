package tymora.myPokedex.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tymora.myPokedex.data.remote.model.PokemonBrief

interface PokedexRepository {

    fun getPokemonPaged(): Flow<PagingData<PokemonBrief>>

}