package com.sk.outlay.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.entities.Category
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
abstract class CategoryDao : BaseDao<Category> {
    @Query("""SELECT * FROM tblCategory""")
    abstract fun getCategories(): Flow<List<Category>>

    @Query("""SELECT * FROM tblCategory WHERE id = :id""")
    abstract suspend fun getCategory(id: UUID): Category
}