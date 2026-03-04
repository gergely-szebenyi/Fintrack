package com.prekogdevs.fintrack.data.remote

import com.prekogdevs.fintrack.data.TransactionRepository
import com.prekogdevs.fintrack.domain.Transaction

class RemoteTransactionRepository(
    private val api: FintrackApi = FintrackApi()
) : TransactionRepository {
    override suspend fun getTransactions(): List<Transaction> =
        api.getTransactions().map { it.toDomain() }
}
