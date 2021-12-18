package com.sk.outlay.data.models

import androidx.room.ColumnInfo

data class TotalExpensesAmount(
    @ColumnInfo(name = "SUM(tblTransaction.amount)")
    val totalAmount: Float
)