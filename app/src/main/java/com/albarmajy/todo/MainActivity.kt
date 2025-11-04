package com.albarmajy.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.albarmajy.todo.data.repository.TaskRepositoryImpl
import com.albarmajy.todo.ui.TodoScreen
import com.albarmajy.todo.ui.TodoViewModel
import com.albarmajy.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val repo = TaskRepositoryImpl(this)
//        val viewModel = TodoViewModel(repo)
//        viewModel.loadTasks()

        setContent {
            ToDoTheme {
//                TodoScreen(viewModel)
            }
        }
    }
}

