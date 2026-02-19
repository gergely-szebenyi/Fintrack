package com.prekogdevs.fintrack.domain

data class Transaction(
    val id: String,
    val name: String,
    val date: String,
    val amount: Double,
    val category: String,
    val icon: String
)