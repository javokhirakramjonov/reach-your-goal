package presentation.screen.main.mvi.ui

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import domain.enums.TaskStatus
import utils.icon
import utils.name

@Composable
fun StatusSelector(
    modifier: Modifier = Modifier,
    status: TaskStatus,
    onStatusChanged: (TaskStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
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