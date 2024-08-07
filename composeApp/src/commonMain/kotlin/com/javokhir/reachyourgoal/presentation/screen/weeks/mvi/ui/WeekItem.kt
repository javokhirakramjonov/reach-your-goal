package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.entity.Week

@Composable
fun WeekItem(
    modifier: Modifier = Modifier,
    week: Week,
    onTaskClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .then(
                Modifier
                    .clip(MaterialTheme.shapes.large)
                    .clickable(onClick = onTaskClick)
            ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = week.getNameComposable(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}