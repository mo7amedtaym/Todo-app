package com.albarmajy.todo.domain.usecase

import com.albarmajy.todo.domain.repository.TaskRepository

class GetTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke() = repository.getAllTasks()
}