package com.albarmajy.todo.data.repository

import android.content.Context
import androidx.room.Room
import com.albarmajy.todo.data.local.TaskDao
import com.albarmajy.todo.domain.model.Task
import com.albarmajy.todo.data.local.TaskDatabase
import com.albarmajy.todo.data.local.TaskEntity
import com.albarmajy.todo.domain.repository.TaskRepository

class TaskRepositoryImpl( private val dao: TaskDao): TaskRepository {

    override suspend fun getAllTasks(): List<Task> {
        return dao.getAllTasks().map { it.toDomain() }
    }

    override suspend fun addTask(task: Task){
        dao.insertTask(TaskEntity.fromDomain(task))
    }

    override suspend fun toggleTask(task: Task) {
        dao.updateTask(TaskEntity.fromDomain(task.copy(isDone = !task.isDone)))
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(TaskEntity.fromDomain(task))
    }
}