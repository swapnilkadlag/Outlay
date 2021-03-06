package com.sk.outlay.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sk.outlay.R

private val primaryFont = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val secondaryFont = FontFamily(
    Font(R.font.jb_mono_medium, FontWeight.Medium),
    Font(R.font.jb_mono_semibold, FontWeight.SemiBold),
    Font(R.font.jb_mono_bold, FontWeight.Bold)
)

val baselineShift = BaselineShift(-0.1125f)

val typography = typographyFromDefaults(
    h1 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        baselineShift = baselineShift,
    ),
    h2 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        baselineShift = baselineShift,
    ),
    h3 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        baselineShift = baselineShift,
    ),
    h4 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        baselineShift = baselineShift,
    ),
    h5 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        baselineShift = baselineShift,
    ),
    h6 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.W500,
        lineHeight = 28.sp,
        baselineShift = baselineShift,
    ),
    subtitle1 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.W500,
        lineHeight = 22.sp,
        baselineShift = baselineShift,
    ),
    subtitle2 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.W500,
        baselineShift = baselineShift,
    ),
    body1 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        baselineShift = baselineShift,
    ),
    body2 = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        baselineShift = baselineShift,
    ),
    button = TextStyle(
        fontFamily = primaryFont,
        fontWeight = FontWeight.Bold,
        baselineShift = baselineShift,
    ),
    caption = TextStyle(
        fontFamily = primaryFont,
        baselineShift = baselineShift,
    ),
    overline = TextStyle(
        letterSpacing = 0.08.em,
        baselineShift = baselineShift,
    )
)

fun typographyFromDefaults(
    h1: TextStyle?,
    h2: TextStyle?,
    h3: TextStyle?,
    h4: TextStyle?,
    h5: TextStyle?,
    h6: TextStyle?,
    subtitle1: TextStyle?,
    subtitle2: TextStyle?,
    body1: TextStyle?,
    body2: TextStyle?,
    button: TextStyle?,
    caption: TextStyle?,
    overline: TextStyle?
): Typography {
    val defaults = Typography()
    return Typography(
        h1 = defaults.h1.merge(h1),
        h2 = defaults.h2.merge(h2),
        h3 = defaults.h3.merge(h3),
        h4 = defaults.h4.merge(h4),
        h5 = defaults.h5.merge(h5),
        h6 = defaults.h6.merge(h6),
        subtitle1 = defaults.subtitle1.merge(subtitle1),
        subtitle2 = defaults.subtitle2.merge(subtitle2),
        body1 = defaults.body1.merge(body1),
        body2 = defaults.body2.merge(body2),
        button = defaults.button.merge(button),
        caption = defaults.caption.merge(caption),
        overline = defaults.overline.merge(overline)
    )
}
