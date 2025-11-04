package com.albarmajy.todo.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albarmajy.todo.domain.model.Task
import com.albarmajy.todo.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCases: TaskUseCases
) : ViewModel() {

    val tasks = mutableStateListOf<Task>()
    val selectedTasks = mutableStateListOf<Task>()

    fun loadTasks() {
        viewModelScope.launch {
            tasks.clear()
            tasks.addAll(useCases.getTasks())
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            useCases.addTask(title)
            loadTasks()
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            useCases.toggleTask(task)
            loadTasks()
        }
    }
    fun toggleSelection(task: Task) {
        if (selectedTasks.contains(task))
            selectedTasks.remove(task)
        else
            selectedTasks.add(task)
    }

    fun clearSelection() {
        selectedTasks.clear()
    }

    fun selectAll(){
        selectedTasks.clear()
        selectedTasks.addAll(tasks)
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            useCases.deleteTask(task)
            loadTasks()
        }
    }

    fun deleteSelected(tasksToDelete : List<Task>) {
        viewModelScope.launch {

            tasksToDelete.forEach { task ->
                useCases.deleteTask(task)
            }
            loadTasks()
        }
    }
}