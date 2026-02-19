package com.prekogdevs.fintrack.feature.dashboard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors


@Composable
fun RecentTransactionsHeader() {
    Text(
        text = "Recent Transactions",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.TextPrimary,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
    )
}