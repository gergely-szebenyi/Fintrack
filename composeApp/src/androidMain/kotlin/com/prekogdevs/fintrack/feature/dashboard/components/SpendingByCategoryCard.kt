package com.prekogdevs.fintrack.feature.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prekogdevs.fintrack.core.theme.AppColors
import com.prekogdevs.fintrack.core.ui.CategoryLegendRow
import com.prekogdevs.fintrack.core.ui.DonutChart
import com.prekogdevs.fintrack.domain.CategorySpend
import kotlin.collections.forEach


@Composable
fun SpendingByCategoryCard(categories: List<CategorySpend>) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                "Spending by Category",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Spacer(Modifier.height(20.dp))

            DonutChart(
                categories = categories,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))

            // Legend
            categories.forEach { cat ->
                CategoryLegendRow(category = cat)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}