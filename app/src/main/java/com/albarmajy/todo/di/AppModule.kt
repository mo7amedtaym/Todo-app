package com.albarmajy.todo.di

import android.app.Application
import androidx.room.Room
import com.albarmajy.todo.data.local.TaskDao
import com.albarmajy.todo.data.local.TaskDatabase
import com.albarmajy.todo.data.repository.TaskRepositoryImpl
import com.albarmajy.todo.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TaskDatabase =
        Room.databaseBuilder(app, TaskDatabase::class.java, "todo_db").build()

    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()

    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository =
        TaskRepositoryImpl(dao)
}