package tymora.myPokedex.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import tymora.myPokedex.ui.components.TypePlate
import tymora.myPokedex.ui.components.horizontalGradientBrush
import tymora.myPokedex.ui.viewmodel.PokemonDetailsViewModel
import tymora.myPokedex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetails(
    navController: NavController,
    pokemonId: Int,
    viewModel: PokemonDetailsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pokemon = state.pokemon
    val brush =
        remember(pokemon?.id, pokemon?.types) { horizontalGradientBrush(pokemon) }

    LaunchedEffect(pokemonId) {
        viewModel.loadPokemon(pokemonId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(4),
            topBar = {
                TopAppBar(
                    title = {
                        Text(pokemon?.name?.replaceFirstChar { it.uppercase() } ?: "No name")
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
            }
        ) { innerPadding ->
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(8.dp, brush),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                when {
                    state.loading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Loading…")
                        }
                    }

                    state.errorMessage != null -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(state.errorMessage!!)
                                //Spacer(Modifier.height(12.dp))
                            }
                        }
                    }

                    pokemon != null ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                AsyncImage(
                                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png",
                                    contentDescription = pokemon.name,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxWidth().sizeIn(maxHeight=320.dp)
                                )
                            }

                            // Плашки типов
                            Spacer(Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                pokemon.types
                                    .sortedBy { it.slot }
                                    .forEach { entry ->
                                        TypePlate(entry.type.name)
                                    }
                            }

                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(SpanStyle(brush)) {
                                        append("About")
                                    }
                                },
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(Modifier.height(12.dp))

                            val weightText = pokemon.weight
                                .let {
                                    String.format(
                                        java.util.Locale.getDefault(),
                                        "%.1f kg",
                                        it / 10.0
                                    )
                                }
                            val heightText = pokemon.height
                                .let {
                                    String.format(
                                        java.util.Locale.getDefault(),
                                        "%.1f m",
                                        it / 10.0
                                    )
                                }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Weight
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_weight),
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = Color.Unspecified
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Text(
                                            weightText,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        "Weight",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                VerticalDivider(
                                    modifier = Modifier.fillMaxHeight(),
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )

                                // Height
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_height),
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = Color.Unspecified
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Text(
                                            heightText,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        "Height",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(Modifier.height(16.dp))
                        }
                }
            }
        }
    }
}