package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.utils.icon
import com.javokhir.reachyourgoal.utils.name

@Composable
fun StatusSelector(
    modifier: Modifier = Modifier,
    status: TaskStatus,
    onStatusChanged: (TaskStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clickable {
                expanded = true
            }
            .padding(4.dp),
    ) {
        status.icon()
    }

    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        TaskStatus.entries.forEach { newStatus ->
            DropdownMenuItem(
                text = { Text(newStatus.name()) },
                onClick = {
                    onStatusChanged(newStatus)
                    expanded = false
                },
                leadingIcon = {
                    newStatus.icon()
                }
            )
        }
    }
}