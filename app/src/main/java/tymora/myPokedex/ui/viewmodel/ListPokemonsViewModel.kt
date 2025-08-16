package tymora.myPokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.domain.PokedexRepository

class ListPokemonsViewModel(
   private val repo: PokedexRepository
): ViewModel() {

    val pokemons = repo.getPokemonPaged().cachedIn(viewModelScope)


    private val _minis = MutableStateFlow<Map<String, MiniDataPokemon>>(emptyMap())


    fun miniFlow(name: String): StateFlow<MiniDataPokemon?> =
        _minis
            .map { it[name] }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)


    fun loadMiniInfo(name: String) {
        viewModelScope.launch {

            if (_minis.value.containsKey(name)) return@launch
            try {
                val mini = repo.getMiniInfo(name)
                _minis.update { it + (name to mini) }
            } catch (e: Exception) {
            }
        }
    }
}