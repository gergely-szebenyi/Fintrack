package com.prekogdevs.fintrack.feature.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors

@Composable
fun BalanceCard(totalBalance: Double, income: Double, expenses: Double) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(listOf(AppColors.BlueDark, AppColors.BlueLight))
            )
            .padding(24.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("$  ", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                Text("Total Balance", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "$${"%.2f".format(totalBalance)}",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BalanceStat(
                    label = "Income",
                    amount = income,
                    isIncome = true,
                    modifier = Modifier.weight(1f)
                )
                BalanceStat(
                    label = "Expenses",
                    amount = expenses,
                    isIncome = false,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}