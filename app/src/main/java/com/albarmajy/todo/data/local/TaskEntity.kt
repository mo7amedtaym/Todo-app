package com.albarmajy.todo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albarmajy.todo.domain.model.Task

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isDone: Boolean = false
) {
    fun toDomain(): Task = Task(id, title, isDone)
    companion object {
        fun fromDomain(task: Task) = TaskEntity(task.id, task.title, task.isDone)
    }
}