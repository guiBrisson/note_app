package me.brisson.note_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.note_app.R

val montserrat = FontFamily(
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography()