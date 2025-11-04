package com.albarmajy.todo.domain.usecase

import com.albarmajy.todo.domain.model.Task
import com.albarmajy.todo.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}