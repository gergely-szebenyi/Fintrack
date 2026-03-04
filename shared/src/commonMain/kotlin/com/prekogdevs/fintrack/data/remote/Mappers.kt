package com.prekogdevs.fintrack.data.remote

import com.prekogdevs.fintrack.data.remote.dto.BudgetDto
import com.prekogdevs.fintrack.data.remote.dto.CategoryDto
import com.prekogdevs.fintrack.data.remote.dto.TransactionDto
import com.prekogdevs.fintrack.domain.Budget
import com.prekogdevs.fintrack.domain.Category
import com.prekogdevs.fintrack.domain.Transaction
import com.prekogdevs.fintrack.domain.TransactionType
import kotlin.math.abs

fun TransactionDto.toDomain() = Transaction(
    id = id,
    amount = abs(amount),
    description = description,
    category = category,
    type = if (type.equals("Income", ignoreCase = true)) TransactionType.INCOME else TransactionType.EXPENSE,
    date = date,
    currency = currency
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name,
    icon = icon,
    color = color
)

fun BudgetDto.toDomain() = Budget(
    id = id,
    category = category,
    limit = limit,
    spent = spent,
    month = month
)
