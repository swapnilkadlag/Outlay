package com.sk.outlay.data.entities

import androidx.room.*
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.enums.OutlayColor
import java.util.UUID

@Entity(tableName = "tblAccount")
data class Account(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val type: AccountType,
    val details: String,
    val color: OutlayColor,
)