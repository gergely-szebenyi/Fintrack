package com.prekogdevs.fintrack.domain

data class Transaction(
    val id: String = "",
    val amount: Double,
    val description: String,
    val category: String,
    val type: TransactionType,
    val date: String,
    val currency: String
)
