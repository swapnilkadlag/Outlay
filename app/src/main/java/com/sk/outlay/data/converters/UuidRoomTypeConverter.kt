package com.sk.outlay.data.converters

import androidx.room.TypeConverter
import java.util.*

class UuidRoomTypeConverter {
    @TypeConverter
    fun toUuid(value: String?): UUID? {
        return value?.let { UUID.fromString(value) }
    }

    @TypeConverter
    fun fromUuid(uuid: UUID?): String? {
        return uuid?.toString()
    }
}
