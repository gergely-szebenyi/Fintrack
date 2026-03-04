package com.prekogdevs.fintrack.domain

data class Budget(
    val id: String,
    val category: String,
    val limit: Double,
    val spent: Double,
    val month: String
)
