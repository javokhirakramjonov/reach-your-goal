package utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.enums.TaskStatus

@Composable
fun TaskStatus.icon(
    modifier: Modifier = Modifier
) {
    val positiveBackgroundColor = Color(0xFFACE1AF)
    val positiveTintColor = Color(0xFF03C03C)

    val negativeBackgroundColor = Color(0xFFE1ACAF)
    val negativeTintColor = Color(0xFFD32F2F)


    val backgroundColor = when (this) {
        TaskStatus.NOT_STARTED -> positiveBackgroundColor
        TaskStatus.NOT_MANDATORY -> positiveBackgroundColor
        TaskStatus.DONE -> positiveBackgroundColor
        TaskStatus.NOT_COMPLETED -> negativeBackgroundColor
    }
    val tintColor = when (this) {
        TaskStatus.NOT_STARTED -> positiveTintColor
        TaskStatus.NOT_MANDATORY -> positiveTintColor
        TaskStatus.DONE -> positiveTintColor
        TaskStatus.NOT_COMPLETED -> negativeTintColor
    }

    val boxModifier = Modifier
        .size(24.dp)
        .clip(CircleShape)
        .border(1.dp, tintColor, CircleShape)
        .background(backgroundColor)

    val imageVector = when (this) {
        TaskStatus.NOT_STARTED -> null
        TaskStatus.NOT_MANDATORY -> Icons.Default.Add
        TaskStatus.DONE -> Icons.Default.DoneAll
        TaskStatus.NOT_COMPLETED -> Icons.Default.Remove
    }

    Box(modifier.then(boxModifier)) {
        if(imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tintColor
            )
        }
    }
}