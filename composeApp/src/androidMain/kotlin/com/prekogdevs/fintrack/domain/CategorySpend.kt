package com.prekogdevs.fintrack.domain

import androidx.compose.ui.graphics.Color

data class CategorySpend(
    val name: String,
    val amount: Double,
    val percentage: Float,
    val color: Color
)