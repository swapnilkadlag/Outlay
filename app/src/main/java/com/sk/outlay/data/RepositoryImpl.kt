package com.sk.outlay.data

import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.entities.Category
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.models.TotalExpensesAmount
import com.sk.outlay.utils.getColorForString
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db: OutlayDatabase) : Repository {
    override fun getTotalExpenses(): Flow<TotalExpensesAmount> {
        return db.transactionDao().getTotalExpensesForMonth()
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
            color = getColorForString(name),
        )
        db.accountDao().insert(account)
    }

    override suspend fun updateAccount(account: Account) {
        db.accountDao().update(account)
    }

    override fun getCategories(): Flow<List<Category>> {
        return db.categoryDao().getCategories()
    }

    override suspend fun getCategory(id: UUID): Category {
        return db.categoryDao().getCategory(id)
    }

    override suspend fun createCategory(name: String) {
        require(name.isNotBlank())
        val category = Category(
            id = UUID.randomUUID(),
            name = name,
            color = getColorForString(name),
        )
        db.categoryDao().insert(category)
    }

    override suspend fun updateCategory(category: Category) {
        db.categoryDao().update(category)
    }
}
