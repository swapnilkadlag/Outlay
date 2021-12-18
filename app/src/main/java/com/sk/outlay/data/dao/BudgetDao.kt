package com.sk.outlay.data.dao

import androidx.room.Dao
import com.sk.outlay.data.entities.Budget

@Dao
abstract class BudgetDao : BaseDao<Budget> {
}