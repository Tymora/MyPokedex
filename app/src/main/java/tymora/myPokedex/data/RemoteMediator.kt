package tymora.myPokedex.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import tymora.myPokedex.data.local.AppDatabase
import tymora.myPokedex.data.local.entity.PokemonBriefEntity
import tymora.myPokedex.data.mappers.toEntity
import tymora.myPokedex.data.remote.PokedexApi
import kotlin.coroutines.cancellation.CancellationException


@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokedexApi,
    private val db: AppDatabase
) : RemoteMediator<Int, PokemonBriefEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonBriefEntity>
    ): MediatorResult = try {
        val dao = db.pokemonDao()

        val pageSize = state.config.pageSize

        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> dao.lastPosition()?.plus( 1 ) ?: 0
        }

        val resp = api.getAllPokemons(limit = pageSize, offset = offset)
        val list = resp.results

        // позиция = глобальный индекс элемента в общей ленте
        val items = list.mapIndexed { i, brief ->
            brief.toEntity(position = offset + i)
        }

        val count = resp.count                       // если поле есть в твоей модели
        val endReachedByNext = (resp.next == null)
        val endReachedByCount = count != null && (offset + list.size >= count)
        val endReached = endReachedByNext || endReachedByCount || items.isEmpty()

        db.withTransaction {
            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
            }
            dao.upsertAll(items)

        }

        MediatorResult.Success(endOfPaginationReached = endReached)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        MediatorResult.Error(e)
    }
}

