package com.sk.outlay.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sk.outlay.data.enums.BudgetType
import java.util.*

@Entity(
    tableName = "tblBudget",
    indices = [Index("categoryId")],
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.RESTRICT,
        onUpdate = ForeignKey.RESTRICT,
    )]
)
data class Budget(
    @PrimaryKey
    val id: UUID,
    val categoryId: UUID?,
    val amount: Float,
    val type: BudgetType,
)