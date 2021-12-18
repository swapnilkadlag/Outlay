package com.sk.outlay.utils

import com.sk.outlay.data.enums.OutlayColor

fun getColorForString(string: String): OutlayColor {
    require(string.isNotEmpty())
    val value = string.first().lowercaseChar() - 'a'
    return OutlayColor.values()[value % 13]
}