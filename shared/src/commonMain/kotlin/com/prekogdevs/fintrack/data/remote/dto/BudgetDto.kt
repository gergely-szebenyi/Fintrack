package com.prekogdevs.fintrack.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BudgetDto(
    val id: String,
    val category: String,
    val limit: Double,
    val spent: Double,
    val month: String
)
