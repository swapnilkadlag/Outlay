package com.sk.outlay.data.dao

import androidx.room.Dao
import com.sk.outlay.data.entities.Category

@Dao
abstract class CategoryDao : BaseDao<Category> {
}