package com.albarmajy.todo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.albarmajy.todo.domain.model.Task

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    task: Task,
    isSelected: Boolean = false,
    onToggle: () -> Unit,
    onSelect: () -> Unit
) {
    val containerColor by animateColorAsState(
        targetValue = when {
            isSelected -> MaterialTheme.colorScheme.primaryContainer
            task.isDone -> MaterialTheme.colorScheme.surface
            else -> MaterialTheme.colorScheme.surface
        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onToggle() },
                onLongClick = { onSelect() } // 👈 الضغط المطول للتحديد
            ),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() }
            )

            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (task.isDone)
                        TextDecoration.LineThrough else TextDecoration.None
                )
            )
        }
    }
}