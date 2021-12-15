package com.sk.outlay.data.entities

import androidx.room.*
import androidx.room.ForeignKey.RESTRICT
import com.sk.outlay.data.enums.TransactionStatus
import com.sk.outlay.data.enums.TransactionType
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*

@Entity(
    tableName = "tblTransaction",
    indices = [Index("categoryId"), Index("accountId")],
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = RESTRICT,
        onUpdate = RESTRICT,
    ), ForeignKey(
        entity = Account::class,
        parentColumns = ["id"],
        childColumns = ["accountId"],
        onDelete = RESTRICT,
        onUpdate = RESTRICT,
    )]
)
data class Transaction(
    @PrimaryKey
    val id: UUID,
    val description: String,
    val notes: String?,
    val amount: Float,
    val accountId: UUID,
    val categoryId: UUID,
    val date: LocalDate,
    val time: LocalTime,
    val createdDate: LocalDate,
    val createdTime: LocalTime,
    val status: TransactionStatus,
    val type: TransactionType,
)
