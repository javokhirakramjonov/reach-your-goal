package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.utils.progressColor
import kotlin.math.ceil


@Composable
fun SingleProgressBar(
    modifier: Modifier = Modifier,
    completedTaskCount: Int,
    notCompletedTaskCount: Int,
    title: String,
    height: Dp,
) {

    val percent =
        ceil((completedTaskCount * 100f) / (completedTaskCount + notCompletedTaskCount)).toInt()
    val completedHeight = height * percent / 100

    Box(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(TaskStatus.NOT_COMPLETED.progressColor()),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 4.dp)
                .height(completedHeight)
                .background(TaskStatus.DONE.progressColor()),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$percent%",
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}