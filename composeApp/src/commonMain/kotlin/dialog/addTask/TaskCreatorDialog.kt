package dialog.addTask

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
                Text(text = "Create a new task")
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Task name") },
                    maxLines = 2
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Task description") },
                    maxLines = 4
                )
                Button(
                    onClick = {
                        onCreateClicked(name, description.ifEmpty { null })
                        onDismissRequest()
                    }
                ) {
                    Text(text = "Create")
                }
            }
        }
    }
}