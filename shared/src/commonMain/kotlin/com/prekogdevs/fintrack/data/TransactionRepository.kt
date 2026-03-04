package com.prekogdevs.fintrack.data

import com.prekogdevs.fintrack.domain.Transaction

interface TransactionRepository {
    suspend fun getTransactions(): List<Transaction>
}
