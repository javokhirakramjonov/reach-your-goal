package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.mvi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.model.SelectableTask

@Composable
fun SelectableTaskItem(
    modifier: Modifier = Modifier,
    selectableTask: SelectableTask,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.then(Modifier.clickable(onClick = onClick)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = selectableTask.task.name)

            Spacer(modifier = Modifier.weight(1f))

            Checkbox(
                checked = selectableTask.isSelected,
                onCheckedChange = { onClick() }
            )
        }
    }
}