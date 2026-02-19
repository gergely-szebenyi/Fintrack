package com.prekogdevs.fintrack.feature.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors
import com.prekogdevs.fintrack.domain.Transaction


@Composable
fun TransactionItem(transaction: Transaction) {
    val isIncome = transaction.amount > 0

    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon box
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isIncome) AppColors.GreenIncome.copy(alpha = 0.12f)
                        else AppColors.BgGray
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(transaction.icon, fontSize = 22.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.TextPrimary
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = transaction.date,
                    fontSize = 12.sp,
                    color = AppColors.TextSecondary
                )
            }

            Text(
                text = if (isIncome) "+$${"%.2f".format(transaction.amount)}"
                else "-$${"%.2f".format(-transaction.amount)}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = if (isIncome) AppColors.GreenIncome else AppColors.TextPrimary
            )
        }
    }
}