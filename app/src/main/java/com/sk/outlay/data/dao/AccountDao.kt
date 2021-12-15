package com.sk.outlay.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sk.outlay.data.entities.Account
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
abstract class AccountDao : BaseDao<Account> {
    @Query("""SELECT * FROM tblAccount WHERE id = :id""")
    abstract suspend fun getAccount(id: UUID): Account

    @Query("""SELECT * FROM tblAccount""")
    abstract fun getAccounts(): Flow<List<Account>>
}