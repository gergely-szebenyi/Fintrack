package com.prekogdevs.fintrack.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val id: String,
    val amount: Double,
    val description: String,
    val category: String,
    val type: String,
    val date: String,
    val currency: String
)
