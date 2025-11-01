package com.albarmajy.todo.data

import android.content.Context
import androidx.room.Room

class TaskRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "todo_database"
    ).build()

    private val dao = db.taskDao()


    suspend fun getAllTasks(): List<Task> = dao.getAllTasks()

    suspend fun addTask(task:Task){
        dao.insertTask(task)
    }

    suspend fun toggleTask(task:Task) {
        dao.updateTask(task.copy(isDone = !task.isDone))
    }
}