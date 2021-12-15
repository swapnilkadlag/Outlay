package com.sk.outlay.data

import com.sk.outlay.data.dao.TotalExpensesAmount
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.enums.AccountType
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {
    fun getTotalExpenses(): Flow<TotalExpensesAmount>

    fun getAccounts(): Flow<List<Account>>

    suspend fun getAccount(id: UUID): Account

    suspend fun createAccount(name: String, type: AccountType, details: String)

    suspend fun updateAccount(account: Account)
}