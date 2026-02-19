package com.prekogdevs.fintrack.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors
import com.prekogdevs.fintrack.domain.CategorySpend


@Composable
fun CategoryLegendRow(category: CategorySpend) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(category.color)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = category.name,
            fontSize = 14.sp,
            color = AppColors.TextPrimary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$${"%.2f".format(category.amount)}",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "${category.percentage.toInt()}%",
            fontSize = 13.sp,
            color = AppColors.TextSecondary,
            modifier = Modifier.width(36.dp),
            textAlign = TextAlign.End
        )
    }
}