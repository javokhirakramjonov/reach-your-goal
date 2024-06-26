package com.javokhir.reachyourgoal.presentation.screen.plans.mvi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.entity.Plan

@Composable
fun PlanItem(
    modifier: Modifier = Modifier,
    plan: Plan,
    onTaskClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .then(
                Modifier
                    .clip(MaterialTheme.shapes.large)
                    .clickable(onClick = onTaskClick)
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = plan.name,
                overflow = TextOverflow.Ellipsis
            )

            VerticalDivider()

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}