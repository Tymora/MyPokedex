package tymora.myPokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tymora.myPokedex.data.remote.model.pokemon.Pokemon
import tymora.myPokedex.domain.PokedexRepository
import kotlin.coroutines.cancellation.CancellationException

data class PokemonDetailsState(
    val loading: Boolean = false,
    val pokemon: Pokemon? = null,
    val errorMessage: String? = null
)

class PokemonDetailsViewModel(
    private val repository: PokedexRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonDetailsState(loading = true))
    val uiState: StateFlow<PokemonDetailsState> = _uiState.asStateFlow()

    private var lastRequestedId: Int? = null

    fun loadPokemon(id: Int, force: Boolean = false) {
        if (!force && lastRequestedId == id && _uiState.value.pokemon?.id == id) return
        lastRequestedId = id

        Log.d("DETAILS", "VM loadPokemon id=$id")

        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, errorMessage = null) }
            try {
                val pokemon = repository.getPokemonDetails(id)
                _uiState.update { it.copy(loading = false, pokemon = pokemon, errorMessage = null) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        pokemon = null,
                        errorMessage = e.message ?: "Failed to load pokemon details"
                    )
                }
            }
        }
    }

    fun retry() {
        val id = lastRequestedId ?: return
        loadPokemon(id, force = true)
    }
}