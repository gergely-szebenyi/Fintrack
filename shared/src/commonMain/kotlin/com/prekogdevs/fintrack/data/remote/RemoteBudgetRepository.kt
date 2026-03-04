package com.prekogdevs.fintrack.data.remote

import com.prekogdevs.fintrack.data.BudgetRepository
import com.prekogdevs.fintrack.domain.Budget

class RemoteBudgetRepository(
    private val api: FintrackApi = FintrackApi()
) : BudgetRepository {
    override suspend fun getBudgets(): List<Budget> =
        api.getBudgets().map { it.toDomain() }
}
