package tymora.myPokedex.ui.components

import android.R.attr.name
import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tymora.myPokedex.data.remote.model.pokemon.Type
import tymora.myPokedex.R

@Composable
fun TypePlate(type: Type){

    val textType = type.toString()

    Surface(
        shape = RoundedCornerShape(percent = 50),
    ) {
//        Icon(
//            painter = painterResource(R.drawable.{}),
//            contentDescription = null,
//            modifier = Modifier.size(14.dp)
//        )
        Text(
            text = textType.replaceFirstChar { it.uppercase()},
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }

}