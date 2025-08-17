package tymora.myPokedex.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import tymora.myPokedex.data.remote.model.pokemon.Pokemon
import tymora.myPokedex.ui.components.TypePlate
import tymora.myPokedex.ui.components.horizontalGradientBrush
import tymora.myPokedex.ui.viewmodel.PokemonDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetails(
    navController: NavController,
    pokemonName: String?,
    viewModel: PokemonDetailsViewModel = koinViewModel()
) {
    var pokemon by remember { mutableStateOf<Pokemon?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(pokemonName) {
        loading = true; error = null
        runCatching { viewModel.detailPokemon(pokemonName) }.onSuccess { pokemon = it }
            .onFailure { error = it.message }
        loading = false
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                horizontalGradientBrush(pokemon)
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(4),
            topBar = {
                TopAppBar(
                    title = {
                        Text(pokemon?.name?.replaceFirstChar { it.uppercase() }
                            ?: "No name")
                    },
                    navigationIcon = {
                        IconButton({ navController.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Назад",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                )
            }) { innerPadding ->
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(8.dp, horizontalGradientBrush(pokemon)),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier

                    .padding(innerPadding)

                    .fillMaxSize()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        pokemon?.types.orEmpty().sortedBy { it.slot }.forEach { entry ->
                            TypePlate(entry.type.name)
                        }
                    }
                }
                Box(contentAlignment = Alignment.TopCenter) {
                    AsyncImage(
                        model = pokemon?.sprites?.other?.officialArtwork?.front_default,
                        contentDescription = pokemon?.name,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(400.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                }
            }

        }
    }
}
