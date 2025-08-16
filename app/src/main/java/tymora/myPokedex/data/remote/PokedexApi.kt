package tymora.myPokedex.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName
import tymora.myPokedex.data.remote.model.AllPokemons
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.data.remote.model.pokemon.Pokemon

interface PokedexApi{
    @GET("pokemon")
    suspend fun getAllPokemons(
        @Query("limit") limit: Int = 20, //20 покемонов  за один запрос.
        @Query("offset") offset: Int
    ): AllPokemons

    @GET("pokemon")
    suspend fun getPokemon(
        @Query ("name") name: String
    ): Pokemon

    @GET("pokemon")
     suspend fun getMiniDataPokemon(
        @Query ("name") name: String
    ): MiniDataPokemon
}