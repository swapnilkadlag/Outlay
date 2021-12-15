package com.sk.outlay.data.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Query
import com.sk.outlay.data.entities.Transaction
import com.sk.outlay.data.enums.TransactionType
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

@Dao
abstract class TransactionDao : BaseDao<Transaction> {
    @Query(
        """
        SELECT SUM(tblTransaction.amount)
        FROM tblTransaction
        WHERE tblTransaction.type IS :transactionType
        AND tblTransaction.date 
        BETWEEN (SELECT tblCycle.startDate FROM tblCycle WHERE date(:date) BETWEEN date(tblCycle.startDate) AND date(tblCycle.endDate))
        AND (SELECT tblCycle.endDate FROM tblCycle WHERE date(:date) BETWEEN date(tblCycle.startDate) AND date(tblCycle.endDate))
        """
    )
    abstract fun getTotalExpensesFromCurrentCycle(
        transactionType: TransactionType = TransactionType.SENT,
        date: LocalDate = LocalDate.now(),
    ): Flow<TotalExpensesAmount>
}

data class TotalExpensesAmount(
    @ColumnInfo(name = "SUM(tblTransaction.amount)")
    val totalAmount: Float
)