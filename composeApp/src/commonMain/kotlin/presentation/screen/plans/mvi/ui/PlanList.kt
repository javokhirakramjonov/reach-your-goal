package presentation.screen.plans.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.entity.Plan
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanList(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    plans: ImmutableList<Plan>,
    onPlanClick: (Plan) -> Unit,
    onDeleteClick: (Plan) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(plans, key = {it.id}) { plan ->
            PlanItem(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .height(100.dp * columns),
                plan = plan,
                onTaskClick = { onPlanClick(plan) },
                onDeleteClick = { onDeleteClick(plan) }
            )
        }
    }
}