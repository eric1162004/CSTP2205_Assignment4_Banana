package com.example.assignment4.view.reusables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BananaCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    topCurveHeight: Dp = 80.dp,
    curveLeftExtend: Boolean = true,
    contentOffsetY: Dp = 0.dp,
    containerOffsetY: Dp = 0.dp,
    composable: @Composable () -> Unit = {}
) {
    Column(modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .offset(y = -contentOffsetY)
        .offset(y = -containerOffsetY)) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .size(topCurveHeight),
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {

                when {
                    curveLeftExtend -> {
                        moveTo(0f, 0f)

                        cubicTo(
                            width.times(.45f),
                            height.times(0.0f),
                            width.times(.65f),
                            height.times(1.0f),
                            width.times(1.0f),
                            height.times(1.0f)
                        )

                        lineTo(0f, height)
                        lineTo(0f, 0f)

                    }
                    else -> {
                        moveTo(0.0f, height)

                        cubicTo(
                            width.times(.45f),
                            height.times(1.0f),
                            width.times(.65f),
                            height.times(0.0f),
                            width.times(1.0f),
                            height.times(0.0f)
                        )

                        lineTo(width, height)
                        lineTo(0.0f, height)
                    }
                }


                close()
            }

            drawPath(path = path, color = backgroundColor)
        }

        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxWidth()
                .offset(y = contentOffsetY)
        ) {
            composable()
        }
    }
}