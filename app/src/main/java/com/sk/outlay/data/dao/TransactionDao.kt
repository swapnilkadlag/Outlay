package com.sk.outlay.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sk.outlay.data.entities.Transaction
import com.sk.outlay.data.enums.TransactionType
import com.sk.outlay.data.models.TotalExpensesAmount
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

@Dao
abstract class TransactionDao : BaseDao<Transaction> {
    @Query(
        """
        SELECT SUM(tblTransaction.amount)
        FROM tblTransaction
        WHERE tblTransaction.type IS :transactionType
        AND strftime('%Y%m', tblTransaction.date) = strftime('%Y%m', :date)
        """
    )
    abstract fun getTotalExpensesForMonth(
        transactionType: TransactionType = TransactionType.Sent,
        date: LocalDate = LocalDate.now(),
    ): Flow<TotalExpensesAmount>
}