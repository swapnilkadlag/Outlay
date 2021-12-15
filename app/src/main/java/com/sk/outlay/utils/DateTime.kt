package com.sk.outlay.utils

import org.threeten.bp.LocalDate

fun LocalDate.monthStartDate(): LocalDate {
    return withDayOfMonth(1)
}

fun LocalDate.monthEndDate(): LocalDate {
    return withDayOfMonth(lengthOfMonth())
}