package com.albarmajy.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.albarmajy.todo.presentation.TodoScreen
import com.albarmajy.todo.presentation.TodoViewModel
import com.albarmajy.todo.ui.theme.ToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadTasks()

        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                TodoScreen(viewModel)
            }
        }
    }
}

