package tymora.myPokedex.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import tymora.myPokedex.data.PokemonRemoteMediator
import tymora.myPokedex.data.local.AppDatabase


@OptIn(ExperimentalPagingApi::class)
fun PokemonsPaging(
    api: PokedexApi,
    db: AppDatabase
) = Pager(
    config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    ),
    remoteMediator = PokemonRemoteMediator(api, db),
    pagingSourceFactory = { db.pokemonDao().pagingSource() }
)