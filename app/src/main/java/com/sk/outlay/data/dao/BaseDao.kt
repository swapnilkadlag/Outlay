package com.sk.outlay.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = ABORT)
    suspend fun insert(obj: T)

    @Insert(onConflict = ABORT)
    suspend fun insert(objs: List<T>)

    @Update(onConflict = ABORT)
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}