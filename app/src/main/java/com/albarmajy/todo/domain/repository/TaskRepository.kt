package com.albarmajy.todo.domain.repository

import com.albarmajy.todo.domain.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun addTask(task: Task)
    suspend fun toggleTask(task: Task)
    suspend fun deleteTask(task: Task)
}