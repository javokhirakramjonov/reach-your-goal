package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.mvi.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.SelectableTask
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SelectableTasks(
    modifier: Modifier = Modifier,
    selectableTasks: ImmutableList<SelectableTask>,
    onTaskSelectionChanged: (taskId: Int, isSelected: Boolean) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(selectableTasks, key = { it.task.id }) {
            SelectableTaskItem(
                modifier = Modifier.clip(MaterialTheme.shapes.medium),
                selectableTask = it,
                onClick = { onTaskSelectionChanged(it.task.id, !it.isSelected) }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}