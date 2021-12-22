package com.sk.outlay.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sk.outlay.data.enums.OutlayColor
import java.util.*

@Entity(tableName = "tblCategory")
data class Category(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val color: OutlayColor,
)