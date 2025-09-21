package tymora.myPokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.data.remote.model.PokemonBrief
import tymora.myPokedex.domain.PokedexRepository


class ListPokemonsViewModel(
    private val repo: PokedexRepository
) : ViewModel() {

    val pokemons: Flow<PagingData<PokemonBrief>> =
        repo.getPokemonPaged().cachedIn(viewModelScope)

    private val _minis = MutableStateFlow<Map<String, MiniDataPokemon>>(emptyMap())
    fun miniFlow(name: String): Flow<MiniDataPokemon?> =
        _minis.map { it[name] }.distinctUntilChanged()

    private val _miniErrors = MutableSharedFlow<Pair<String, Throwable>>(replay = 0, extraBufferCapacity = 1)

    private val inFlight = mutableMapOf<String, Deferred<MiniDataPokemon>>()
    private val flightMutex = Mutex()

    fun loadMiniInfo(name: String) {
        viewModelScope.launch {
            if (_minis.value.containsKey(name)) return@launch

            val task = flightMutex.withLock {
                val existing = inFlight[name]
                if (existing != null && existing.isActive) {
                    existing
                } else {
                    async(Dispatchers.IO) { repo.getMiniInfo(name) }
                        .also { inFlight[name] = it }
                }
            }

            try {
                val mini = task.await()
                _minis.update { it + (name to mini) }
            } catch (e: Throwable) {
                _miniErrors.tryEmit(name to e)
            } finally {
                flightMutex.withLock { inFlight.remove(name) }
            }
        }
    }
}