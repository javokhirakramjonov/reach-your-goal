package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeekList(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    weeks: ImmutableList<Week>,
    onWeekClick: (Week) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(weeks, key = { it.id }) { week ->
            WeekItem(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth(),
                week = week,
                onTaskClick = { onWeekClick(week) },
            )
        }
    }
}