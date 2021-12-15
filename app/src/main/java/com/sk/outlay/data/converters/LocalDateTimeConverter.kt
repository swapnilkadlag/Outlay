package com.sk.outlay.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Named

@ProvidedTypeConverter
class LocalDateTimeConverter(
    @Named("DbDateFormat")
    var dbDateFormatter: DateTimeFormatter,
    @Named("DbTimeFormat")
    var dbTimeFormatter: DateTimeFormatter,
    @Named("DbDateTimeFormat")
    var dbDateTimeFormat: DateTimeFormatter
) {

    @TypeConverter
    fun stringToDate(value: String?): LocalDate? {
        return if (value == null) null else LocalDate.parse(value, dbDateFormatter)
    }

    @TypeConverter
    fun dateToString(value: LocalDate?): String? {
        return value?.format(dbDateFormatter)
    }

    @TypeConverter
    fun stringToTime(value: String?): LocalTime? {
        return if (value == null) null else LocalTime.parse(value, dbTimeFormatter)
    }

    @TypeConverter
    fun timeToString(value: LocalTime?): String? {
        return value?.format(dbTimeFormatter)
    }


    @TypeConverter
    fun stringToDateTime(value: String?): LocalDateTime? {
        return if (value == null) null else LocalDateTime.parse(value, dbDateTimeFormat)
    }

    @TypeConverter
    fun dateTimeToString(value: LocalDateTime?): String? {
        return value?.format(dbDateTimeFormat)
    }
}