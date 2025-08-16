    package tymora.myPokedex.ui.components

    import android.R.attr.onClick
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.material3.Card
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.ColorFilter
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.paging.compose.collectAsLazyPagingItems
    import coil.compose.AsyncImage
    import okio.AsyncTimeout
    import tymora.myPokedex.data.remote.model.PokemonBrief
    import tymora.myPokedex.ui.viewmodel.ListPokemonsViewModel
    import tymora.myPokedex.R


    @Composable
    fun PokemonBox(pokemonBrief: PokemonBrief,
                   viewModel: ListPokemonsViewModel,
                   onClick: () -> Unit) {
        val pokemon by viewModel.onePokemon.collectAsState()

        LaunchedEffect(pokemonBrief.name) {
            viewModel.loadMiniInfo(pokemonBrief.name)
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            Box {

                AsyncImage(
                    model = pokemon?.sprites?.front_default,

                    contentDescription = pokemon?.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
            }
        }

    }