package com.albarmajy.todo.domain.usecase

import com.albarmajy.todo.domain.model.Task
import com.albarmajy.todo.domain.repository.TaskRepository

class AddTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(title: String) {
        repository.addTask(Task(title = title))
    }
}