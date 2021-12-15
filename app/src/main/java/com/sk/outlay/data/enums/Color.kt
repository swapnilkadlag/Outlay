package com.sk.outlay.data.enums

import java.util.*

enum class OutlayColor {
    Red,
    Pink,
    Purple,
    DeepPurple,
    Indigo,
    Blue,
    LightBlue,
    Cyan,
    Teal,
    Green,
    LightGreen,
    Lime,
    Yellow,
    Amber,
    Orange,
    DeepOrange,
}

fun getRandomOutlayColor(): OutlayColor {
    val random = Random()
    val value = random.nextInt(15)
    return OutlayColor.values()[value]
}