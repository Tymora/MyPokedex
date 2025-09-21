package tymora.myPokedex.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tymora.myPokedex.data.remote.model.PokemonBrief
import tymora.myPokedex.ui.viewmodel.ListPokemonsViewModel


@Composable
fun PokemonBox(
    pokemonBrief: PokemonBrief,
    viewModel: ListPokemonsViewModel,
    onClick: () -> Unit
) {
    val name = pokemonBrief.name
    val pokemon by viewModel.miniFlow(name).collectAsState(initial = null)

    LaunchedEffect(pokemonBrief.name) {
        viewModel.loadMiniInfo(pokemonBrief.name)
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .aspectRatio(1f) //масштабирует по размеру картинки?
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(14)
                    )
            )


            Text(
                text = "#${pokemon?.id ?: "..."}",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            )

            AsyncImage(
                model = pokemon?.sprites?.other?.officialArtwork?.front_default,
                contentDescription = pokemon?.name,
                modifier = Modifier
                    .padding(bottom = 14.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(
                    text = name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}