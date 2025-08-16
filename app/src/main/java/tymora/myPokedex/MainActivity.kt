package tymora.myPokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tymora.myPokedex.ui.screens.ListPokemons
import tymora.myPokedex.ui.screens.PokemonDetails
import tymora.myPokedex.ui.theme.MyPokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable("list") {
                        ListPokemons(navController = navController)
                    }

                    composable(
                        route = "details/{name}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name")
                        PokemonDetails(
                            navController = navController,
                            pokemonName = name
                        )
                    }
                }

            }
        }
    }
}
