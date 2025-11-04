package com.albarmajy.todo.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.albarmajy.todo.ui.components.TaskItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var taskTitle by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("قائمة المهام", style = MaterialTheme.typography.titleLarge) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
//        floatingActionButton = {
//            if (!viewModel.selectedTasks.isEmpty()) {
//                viewModel.deleteSelected()
//            }
//        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val keyboardController = LocalSoftwareKeyboardController.current
            if (viewModel.selectedTasks.isEmpty()) {
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = {
                        taskTitle = it
                    },
                    label = { Text("أضف مهمة جديدة") },
                    modifier = Modifier.fillMaxWidth(),

                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),

                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (taskTitle.isNotBlank()) {
                                viewModel.addTask(taskTitle)
                                taskTitle = ""
                            }
//                            keyboardController?.hide()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
            else{
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            viewModel.deleteSelected(viewModel.selectedTasks.toList())
                            viewModel.clearSelection()
                        }
                    )

                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.clickable {
                            viewModel.clearSelection()
                        }
                    )

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Select All",
                        modifier = Modifier.clickable {
                            viewModel.selectAll()
                        }
                    )
                }

            }

            if (viewModel.tasks.isEmpty()) {
                Text("لا توجد مهام حالياً", style = MaterialTheme.typography.bodyLarge)
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.tasks, key = { it.id }) { task ->
                    AnimatedVisibility(visible = true) {
                        TaskItem(
                            task = task,
                            isSelected = viewModel.selectedTasks.contains(task),
                            onToggle = {
                                if (viewModel.selectedTasks.isNotEmpty())
                                    viewModel.toggleSelection(task)
                                else
                                    viewModel.toggleTask(task)

                            },
                            onSelect = { viewModel.toggleSelection(task) }
                        )
                    }
                }
            }
            var done = viewModel.tasks.filter { it.isDone }
            if(done.isNotEmpty()){
                TextButton({

                    viewModel.deleteSelected(done)
                }) {
                    Text("Delete completed tasks.")
                }
            }
        }
    }
}