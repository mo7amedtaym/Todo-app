package com.albarmajy.todo.domain.usecase

data class TaskUseCases(
    val getTasks: GetTasksUseCase,
    val addTask: AddTaskUseCase,
    val toggleTask: ToggleTaskUseCase,
    val deleteTask: DeleteTaskUseCase
)