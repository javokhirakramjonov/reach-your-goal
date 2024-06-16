package screen.plans.mvi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import domain.Plan

@Composable
fun PlanItem(
    modifier: Modifier = Modifier,
    plan: Plan,
    onTaskClick: () -> Unit
) {
    Card(
        modifier = modifier
            .then(
                Modifier
                    .clip(MaterialTheme.shapes.large)
                    .clickable(onClick = onTaskClick)
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = plan.name,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}