package com.albarmajy.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albarmajy.todo.domain.model.Task

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}