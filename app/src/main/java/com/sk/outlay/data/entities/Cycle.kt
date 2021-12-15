package com.sk.outlay.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import java.util.*

@Entity(tableName = "tblCycle")
data class Cycle(
    @PrimaryKey
    val id: UUID,
    val startDate: LocalDate,
    val endDate: LocalDate,
)