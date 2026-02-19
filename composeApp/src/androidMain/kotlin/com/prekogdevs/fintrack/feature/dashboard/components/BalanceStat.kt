package com.prekogdevs.fintrack.feature.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors

@Composable
fun BalanceStat(label: String, amount: Double, isIncome: Boolean, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.15f))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (isIncome) "↗ " else "↘ ",
                color = if (isIncome) AppColors.GreenIncome else AppColors.RedExpense,
                fontSize = 14.sp
            )
            Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "$${"%.2f".format(amount)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}