package tymora.myPokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import tymora.myPokedex.R

val Poppins = FontFamily(
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_regular, weight = FontWeight.Normal)
)


val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),

    bodyLarge = TextStyle(  //Subtitle 1
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
    ),
    bodyMedium = TextStyle( //Subtitle 2
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    bodySmall = TextStyle( //Subtitle 3
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),

    labelLarge = TextStyle( //Body 1
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    ),

    labelMedium = TextStyle( //Body 2
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),

    labelSmall = TextStyle( //Body 3
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),

    displaySmall = TextStyle( //Caption
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp
    ),




)