package com.prekogdevs.fintrack.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.core.graphics.toColorInt
import com.prekogdevs.fintrack.domain.CategorySpend

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    categories: List<CategorySpend>
) {
    val total = categories.sumOf { it.amount }.toFloat()
    Canvas(modifier = modifier) {
        val strokeWidth = 40f
        val radius = (size.minDimension - strokeWidth) / 2f
        val center = Offset(size.width / 2f, size.height / 2f)
        var startAngle = -90f

        categories.forEach { spend ->
            val fullSweep = (spend.amount.toFloat() / total) * 360f
            val drawSweep = fullSweep - 2f
            if (drawSweep > 0f) {
                drawArc(
                    color = Color(spend.category.color.toColorInt()),
                    startAngle = startAngle,
                    sweepAngle = drawSweep,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
            }
            startAngle += fullSweep
        }
    }
}
