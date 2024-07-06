package com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.theme.ThemeType
import com.javokhir.reachyourgoal.utils.composableName

@Composable
fun ThemeTypeItem(
    modifier: Modifier = Modifier,
    themeType: ThemeType,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    Card(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onSelected)
            .then(modifier),
        border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colorScheme.primary) else null
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = themeType.composableName()
            )
        }
    }
}