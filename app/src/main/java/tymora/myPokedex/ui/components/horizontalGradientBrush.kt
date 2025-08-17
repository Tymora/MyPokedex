package tymora.myPokedex.ui.components

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import tymora.myPokedex.data.remote.model.pokemon.Pokemon


fun horizontalGradientBrush(
    pokemon: Pokemon?,
    fallback: Color = Color.Transparent
): Brush {
    val types = pokemon?.types.orEmpty().sortedBy { it.slot }
    val color1 = colorMapper(types.getOrNull(0)?.type?.name) ?: fallback
    val color2 =
        colorMapper(types.getOrNull(1)?.type?.name)?.takeUnless { it == Color.Transparent }  // Если второго цвета нет(от маппера приходит прозрачный) используем первый
            ?: color1
    return Brush.horizontalGradient(listOf(color1, color2))
}