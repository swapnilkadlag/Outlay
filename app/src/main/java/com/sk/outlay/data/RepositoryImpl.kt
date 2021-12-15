package com.sk.outlay.data

import com.sk.outlay.data.dao.TotalExpensesAmount
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.enums.getRandomOutlayColor
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db: OutlayDatabase) : Repository {
    override fun getTotalExpenses(): Flow<TotalExpensesAmount> {
        return db.transactionDao().getTotalExpensesFromCurrentCycle()
    }

    override suspend fun getAccount(id: UUID): Account {
        return db.accountDao().getAccount(id)
    }

    override fun getAccounts(): Flow<List<Account>> {
        return db.accountDao().getAccounts()
    }

    override suspend fun createAccount(name: String, type: AccountType, details: String) {
        require(name.isNotBlank())
        val account = Account(
            id = UUID.randomUUID(),
            name = name,
            type = type,
            details = details,
            color = getRandomOutlayColor(),
        )
        db.accountDao().insert(account)
    }

    override suspend fun updateAccount(account: Account) {
        db.accountDao().update(account)
    }
}
