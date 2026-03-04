package com.prekogdevs.fintrack.data

import com.prekogdevs.fintrack.domain.Budget

interface BudgetRepository {
    suspend fun getBudgets(): List<Budget>
}
