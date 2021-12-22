package com.sk.outlay.data

import com.sk.outlay.data.entities.*
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.enums.BudgetType
import com.sk.outlay.data.enums.TransactionStatus
import com.sk.outlay.data.enums.TransactionType
import com.sk.outlay.utils.getColorForString
import com.sk.outlay.utils.monthEndDate
import com.sk.outlay.utils.monthStartDate
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*

object SeedData {
    val accounts = listOf(
        Account(
            id = UUID.randomUUID(),
            name = "Cash",
            type = AccountType.Cash,
            details = "",
            color = getColorForString("Cash"),
        ),
        Account(
            id = UUID.randomUUID(),
            name = "Other",
            type = AccountType.Other,
            details = "",
            color = getColorForString("Other"),
        ),
    )

    val budget = Budget(
        id = UUID.randomUUID(),
        amount = 0f,
        categoryId = null,
        type = BudgetType.Total,
    )

    val categories = listOf(
        Category(
            id = UUID.randomUUID(),
            name = "Other",
            color = getColorForString("Other"),
        )
    )

    val transaction = Transaction(
        UUID.randomUUID(),
        "transaction",
        "notes",
        5000f,
        accounts.first { a -> a.name == "Other" }.id,
        categories.first { c -> c.name == "Other" }.id,
        LocalDate.now(),
        LocalTime.now(),
        LocalDate.now(),
        LocalTime.now(),
        TransactionStatus.Completed,
        TransactionType.Sent,
    )
}
