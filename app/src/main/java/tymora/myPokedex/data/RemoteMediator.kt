package tymora.myPokedex.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import tymora.myPokedex.data.local.AppDatabase
import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.local.entity.RemoteKeys
import tymora.myPokedex.data.mappers.toEntity
import tymora.myPokedex.data.remote.PokedexApi
import tymora.myPokedex.data.remote.model.PokemonBrief


@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokedexApi,
    private val db: AppDatabase
) : RemoteMediator<Int, PokemonBriefEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonBriefEntity>
    ): MediatorResult = try {
        val keysDao = db.remoteKeysDao()
        val dao = db.pokemonDao()

        val limit = state.config.pageSize
        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> keysDao.get()?.nextOffset ?: 0
        }

        val res = api.getAllPokemons(limit = limit, offset = offset) // DTO: results:[{name,url}]
        val items = res.results.map { PokemonBrief(name = it.name, url = it.url).toEntity() }
        val endReached = items.isEmpty()

        db.withTransaction {
            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
                keysDao.delete()
            }
            dao.upsertAll(items)
            val next = if (endReached) null else offset + limit
            keysDao.upsert(RemoteKeys(nextOffset = next))
        }

        MediatorResult.Success(endOfPaginationReached = endReached)
    } catch (e: Exception) {
        MediatorResult.Error(e)
    }
}
