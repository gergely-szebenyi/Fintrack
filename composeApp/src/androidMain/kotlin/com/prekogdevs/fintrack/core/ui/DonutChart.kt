package com.prekogdevs.fintrack.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.prekogdevs.fintrack.domain.CategorySpend
import kotlin.collections.forEach

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    categories: List<CategorySpend>
) {
    val total = categories.sumOf { it.amount.toFloat().toDouble() }.toFloat()
    Canvas(modifier = modifier) {
        val strokeWidth = 40f
        val radius = (size.minDimension - strokeWidth) / 2f
        val center = Offset(size.width / 2f, size.height / 2f)
        var startAngle = -90f

        categories.forEach { cat ->
            val sweepAngle = (cat.amount.toFloat() / total) * 360f
            drawArc(
                color = cat.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle - 2f, // gap between segments
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
            )
            startAngle += sweepAngle
        }
    }
}