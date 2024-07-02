package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.WeekDay
import com.javokhir.reachyourgoal.utils.name
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.main_screen_task_name

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.header(
    modifier: Modifier = Modifier,
) {
    stickyHeader {
        val density = LocalDensity.current

        var height by remember { mutableStateOf(0.dp) }

        Row(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .then(modifier)
        ) {
            Box(
                modifier = Modifier
                    .onGloballyPositioned {
                        height = with(density) { it.size.height.toDp() }
                    }
                    .weight(1f)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(Res.string.main_screen_task_name))
            }
            WeekDay.entries.forEach {
                Box(
                    modifier = Modifier
                        .height(height)
                        .width(1.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )

                Box(
                    modifier = Modifier
                        .weight(0.4f)
                        .height(height)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.name().first().toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}