package tymora.myPokedex.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import tymora.myPokedex.R
import tymora.myPokedex.ui.theme.*


@Composable
fun iconMapper(type: String): Int {
   return when(type){
        "bug" -> R.drawable.bug
        "dark" -> R.drawable.dark
       "dragon" -> R.drawable.dragon
       "electric" -> R.drawable.electric
       "fairy" -> R.drawable.fairy
       "fighting" -> R.drawable.fighting
       "fire" -> R.drawable.fire
       "flying" -> R.drawable.flying
       "ghost"-> R.drawable.ghost
       "grass" -> R.drawable.grass
       "ground" -> R.drawable.ground
       "ice"-> R.drawable.ice
       "normal" -> R.drawable.normal
       "poison"-> R.drawable.poison
       "psychic" -> R.drawable.psychic
       "rock" -> R.drawable.rock
       "steel" -> R.drawable.steel
       "water" -> R.drawable.water
       else -> R.drawable.siluette
    }
}

fun colorMapper(type: String?): Color {
    return when(type){
        "bug" -> bug
        "dark" -> dark
        "dragon" -> dragon
        "electric" -> electric
        "fairy" -> fairy
        "fighting" -> fighting
        "fire" -> fire
        "flying" -> flying
        "ghost"-> ghost
        "grass" ->grass
        "ground" -> ground
        "ice"-> ice
        "normal" -> normal
        "poison"-> poison
        "psychic" -> psychic
        "rock" -> rock
        "steel" -> steel
        "water" -> water
        else -> Color.Transparent
    }

}