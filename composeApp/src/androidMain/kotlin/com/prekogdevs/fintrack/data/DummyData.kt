package com.prekogdevs.fintrack.data

import com.prekogdevs.fintrack.core.theme.AppColors
import com.prekogdevs.fintrack.domain.CategorySpend
import com.prekogdevs.fintrack.domain.Transaction

// TODO: Use MockAPI models
val dummyTransactions = listOf(
    Transaction("1", "Morning Coffee", "Feb 19, 2026", -5.50, "Coffee", "☕"),
    Transaction("2", "Salary Payment",   "Feb 18, 2026", +3500.00, "Income",    "📈"),
    Transaction("3", "Grocery Shopping", "Feb 17, 2026", -125.80,  "Food",      "🍽️"),
    Transaction("4", "Gas Station",      "Feb 16, 2026", -45.00,   "Transport", "🚗"),
    Transaction("5", "New Shoes",        "Feb 15, 2026", -89.99,   "Shopping",  "🛍️"),
)

val dummyCategories = listOf(
    CategorySpend("Food & Dining", 325.80, 35f, AppColors.Food),
    CategorySpend("Transport",     145.00, 16f, AppColors.Transport),
    CategorySpend("Shopping",      289.99, 31f, AppColors.Shopping),
    CategorySpend("Coffee",         45.50,  5f, AppColors.Coffee),
    CategorySpend("Health",        120.00, 13f, AppColors.Health),
)