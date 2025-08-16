package tymora.myPokedex.data.remote

import androidx.paging.PagingState
import androidx.paging.PagingSource
import retrofit2.HttpException
import tymora.myPokedex.data.remote.model.PokemonBrief
import java.io.IOException

class PokemonsPaging(private val api: PokedexApi, private val pageSize: Int) :
    PagingSource<Int, PokemonBrief>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonBrief>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor)
        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonBrief> {
        val page = params.key ?: 0
        val offset = page * pageSize
        return try {
            val resp = api.getAllPokemons(limit = pageSize, offset = offset)

            val data = resp.results
            val prevKey = if (page == 0) null else page - 1
            val nextKey = page + 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}