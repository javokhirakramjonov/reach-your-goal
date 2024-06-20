package com.javokhir.reachyourgoal.presentation.dialog.addTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.create_task_dialog_description
import reach_your_goal.composeapp.generated.resources.create_task_dialog_name
import reach_your_goal.composeapp.generated.resources.create_task_dialog_positive_button
import reach_your_goal.composeapp.generated.resources.create_task_dialog_title

@Composable
fun TaskCreatorDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onCreateClicked: (name: String, description: String?) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(modifier) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = stringResource(Res.string.create_task_dialog_title))
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = stringResource(Res.string.create_task_dialog_name)) },
                    maxLines = 2
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = stringResource(Res.string.create_task_dialog_description)) },
                    maxLines = 4
                )
                Button(
                    onClick = {
                        onCreateClicked(name, description.ifEmpty { null })
                        onDismissRequest()
                    }
                ) {
                    Text(text = stringResource(Res.string.create_task_dialog_positive_button))
                }
            }
        }
    }
}