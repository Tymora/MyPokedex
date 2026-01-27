package tymora.myPokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import tymora.myPokedex.data.remote.model.PokemonBrief
import tymora.myPokedex.domain.PokedexRepository


class ListPokemonsViewModel(
    repo: PokedexRepository
) : ViewModel() {

    val pokemons: Flow<PagingData<PokemonBrief>> =
        repo.getPokemonPaged().cachedIn(viewModelScope)


}