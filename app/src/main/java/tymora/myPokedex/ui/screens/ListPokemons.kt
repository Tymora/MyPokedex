package tymora.myPokedex.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.compose.viewmodel.koinViewModel
import tymora.myPokedex.ui.components.PokemonBox
import tymora.myPokedex.ui.viewmodel.ListPokemonsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPokemons(
    navController: NavController,
    viewModel: ListPokemonsViewModel = koinViewModel(),
) {
    val pokemons = viewModel.pokemons.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0),
            topBar = {
                TopAppBar(
                    title = { Text("Pokedex", style = MaterialTheme.typography.headlineMedium) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                )
            }
        ) { innerPadding  ->

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(8.dp, MaterialTheme.colorScheme.primary),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier

                    .padding(innerPadding)

                    .fillMaxSize()
            ) {
                if (pokemons.itemCount > 0) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(top = 12.dp),
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            count = pokemons.itemCount,
                            key = { idx -> pokemons[idx]?.name ?: idx }
                        ) { idx ->
                            val brief = pokemons[idx]
                            if (brief != null) {
                                PokemonBox(
                                    pokemonBrief = brief,
                                    viewModel = viewModel,
                                    onClick = { navController.navigate("details/${brief.name}") }
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}