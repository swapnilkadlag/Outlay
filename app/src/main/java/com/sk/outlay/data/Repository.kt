package com.sk.outlay.data

import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.entities.Category
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.models.TotalExpensesAmount
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {
    fun getTotalExpenses(): Flow<TotalExpensesAmount>

    fun getAccounts(): Flow<List<Account>>

    suspend fun getAccount(id: UUID): Account

    suspend fun createAccount(name: String, type: AccountType, details: String)

    suspend fun updateAccount(account: Account)

    fun getCategories(): Flow<List<Category>>

    suspend fun getCategory(id: UUID): Category

    suspend fun createCategory(name: String)

    suspend fun updateCategory(category: Category)

}