package tymora.myPokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.domain.PokedexRepository

class ListPokemonsViewModel(
   private val repo: PokedexRepository
): ViewModel() {

    val pokemons = repo.getPokemonPaged().cachedIn(viewModelScope)

    private val _onePokemon = MutableStateFlow<MiniDataPokemon?>(null)
    val onePokemon: StateFlow<MiniDataPokemon?> = _onePokemon

    fun loadMiniInfo(name: String) {
        viewModelScope.launch {
            _onePokemon.value = repo.getMiniInfo(name)
        }
    }
}