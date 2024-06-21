package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.javokhir.reachyourgoal.domain.entity.Plan
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.current_plan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanSelector(
    modifier: Modifier = Modifier,
    currentPlanName: String,
    plans: ImmutableList<Plan>,
    planSelected: (plan: Plan) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = currentPlanName,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(text = stringResource(Res.string.current_plan)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            plans.forEach { plan ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = plan.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        planSelected(plan)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}