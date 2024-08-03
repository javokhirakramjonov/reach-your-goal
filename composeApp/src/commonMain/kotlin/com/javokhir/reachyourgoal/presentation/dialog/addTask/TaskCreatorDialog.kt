package com.javokhir.reachyourgoal.presentation.dialog.addTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.javokhir.reachyourgoal.AppLocale

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
                Text(text = AppLocale.current.createTaskDialog.title)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = AppLocale.current.createTaskDialog.taskName) },
                    maxLines = 2,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onCreateClicked(name, description.ifEmpty { null })
                            onDismissRequest()
                        }
                    )
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = AppLocale.current.createTaskDialog.taskDescription) },
                    maxLines = 4,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onCreateClicked(name, description.ifEmpty { null })
                            onDismissRequest()
                        }
                    )
                )
                Button(
                    onClick = {
                        onCreateClicked(name, description.ifEmpty { null })
                        onDismissRequest()
                    }
                ) {
                    Text(text = AppLocale.current.commonWords.create)
                }
            }
        }
    }
}