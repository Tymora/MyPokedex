package tymora.myPokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tymora.myPokedex.data.PokemonRemoteMediator
import tymora.myPokedex.data.local.AppDatabase
import tymora.myPokedex.data.remote.PokedexApi
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.domain.PokedexRepository
import tymora.myPokedex.data.mappers.toDomain
import tymora.myPokedex.data.remote.model.PokemonBrief


class PokedexRepositoryImpl(
    private val api: PokedexApi,
    private val db: AppDatabase
) : PokedexRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPaged(): Flow<PagingData<PokemonBrief>> =
        Pager(
            config = PagingConfig(pageSize = 40, enablePlaceholders = false),
            remoteMediator = PokemonRemoteMediator(api, db),
            pagingSourceFactory = { db.pokemonDao().pagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }
     override suspend fun getMiniInfo(name: String): MiniDataPokemon {
         return api.getMiniDataPokemon(name)
    }
}