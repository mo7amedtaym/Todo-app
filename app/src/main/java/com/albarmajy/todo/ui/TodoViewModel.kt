package com.albarmajy.todo.ui

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albarmajy.todo.data.Task
import com.albarmajy.todo.data.TaskRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repo: TaskRepository) : ViewModel() {

    // نحتفظ بالحالة الحالية للمهمات
    val tasks = mutableStateListOf<Task>()

    fun loadTasks(){
        viewModelScope.launch{
            val list = repo.getAllTasks()
            tasks.clear()
            tasks.addAll(list)

        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            val task = Task(title = title)
            repo.addTask(task)
            loadTasks()
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            repo.toggleTask(task)
            loadTasks()
        }
    }
}