package tymora.myPokedex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokedex", style = MaterialTheme.typography.headlineMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }
    ) { paddingValues ->
        if (pokemons.itemCount > 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(paddingValues),
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