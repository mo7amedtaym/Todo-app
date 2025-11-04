package com.albarmajy.todo.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albarmajy.todo.domain.model.Task
import com.albarmajy.todo.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val toggleTaskUseCase: ToggleTaskUseCase
) : ViewModel() {

    val tasks = mutableStateListOf<Task>()

    fun loadTasks() {
        viewModelScope.launch {
            tasks.clear()
            tasks.addAll(getTasksUseCase())
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            addTaskUseCase(title)
            loadTasks()
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            toggleTaskUseCase(task)
            loadTasks()
        }
    }
}