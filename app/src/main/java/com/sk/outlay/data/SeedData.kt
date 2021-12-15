package com.sk.outlay.data

import com.sk.outlay.data.entities.Cycle
import com.sk.outlay.data.entities.*
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.data.enums.TransactionStatus
import com.sk.outlay.data.enums.TransactionType
import com.sk.outlay.data.enums.getRandomOutlayColor
import com.sk.outlay.utils.monthEndDate
import com.sk.outlay.utils.monthStartDate
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*

object SeedData {
    val cycle = Cycle(
        id = UUID.randomUUID(),
        startDate = LocalDate.now().monthStartDate(),
        endDate = LocalDate.now().monthEndDate(),
    )

    val accounts = listOf(
        Account(
            id = UUID.randomUUID(),
            name = "Cash",
            type = AccountType.Cash,
            details = "",
            color = getRandomOutlayColor(),
        ),
        Account(
            id = UUID.randomUUID(),
            name = "Other",
            type = AccountType.Other,
            details = "",
            color = getRandomOutlayColor(),
        ),
    )

    val categories = listOf(
        Category(
            id = UUID.randomUUID(),
            name = "Other",
            limit = 0f,
            color = getRandomOutlayColor(),
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
        TransactionStatus.COMPLETED,
        TransactionType.SENT,
    )
}
