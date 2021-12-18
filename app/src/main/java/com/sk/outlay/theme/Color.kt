package com.sk.outlay.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sk.outlay.data.enums.OutlayColor

val Purple200 = Color(0xFF9FA8DA)
val Purple500 = Color(0xFF3F51B5)
val Purple700 = Color(0xFF303F9F)
val Teal200 = Color(0xFFFFE082)

val backgroundLight1 = Color(0xFFFFFFFF)
val backgroundLight2 = Color(0xFFFBFBFB)
val backgroundLight3 = Color(0xFFF6F6F7)
val accentLight = Color(0xFF03FC96)
val primaryLight = Color(0xFF2321D5)

val backgroundDark1 = Color(0xFF161920)
val backgroundDark2 = Color(0xFF1D2027)
val backgroundDark3 = Color(0xFF22252B)
val accentDark = Color(0xFF1CFDA1)
val primaryDark = Color(0xFF816DD7)

val white = Color(0xFFFFFFFF)
val black = Color(0xFF000000)

@Composable
fun outlayColor(outlayColor: OutlayColor, isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return when (outlayColor) {
        OutlayColor.Red -> if (isDarkTheme) Color(0xFFE57373) else Color(0xFFF44336)
        OutlayColor.Teal -> if (isDarkTheme) Color(0xFF4DB6AC) else Color(0xFF009688)
        OutlayColor.Pink -> if (isDarkTheme) Color(0xFFF06292) else Color(0xFFE91E63)
        OutlayColor.Green -> if (isDarkTheme) Color(0xFF81C784) else Color(0xFF4CAF50)
        OutlayColor.Purple -> if (isDarkTheme) Color(0xFFBA68C8) else Color(0xFF9C27B0)
        OutlayColor.DeepPurple -> if (isDarkTheme) Color(0xFF9575CD) else Color(0xFF673AB7)
        OutlayColor.Lime -> if (isDarkTheme) Color(0xFFDCE775) else Color(0xFFCDDC39)
        OutlayColor.Indigo -> if (isDarkTheme) Color(0xFF7986CB) else Color(0xFF3F51B5)
        OutlayColor.Yellow -> if (isDarkTheme) Color(0xFFFFF176) else Color(0xFFFFEB3B)
        OutlayColor.Blue -> if (isDarkTheme) Color(0xFF64B5F6) else Color(0xFF2196F3)
        OutlayColor.Orange -> if (isDarkTheme) Color(0xFFFFB74D) else Color(0xFFFF9800)
        OutlayColor.Cyan -> if (isDarkTheme) Color(0xFF4DD0E1) else Color(0xFF00BCD4)
        OutlayColor.DeepOrange -> if (isDarkTheme) Color(0xFFFF8A65) else Color(0xFFFF5722)
    }
}