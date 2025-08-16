package tymora.myPokedex.data.remote.model

data class AllPokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonBrief>
)