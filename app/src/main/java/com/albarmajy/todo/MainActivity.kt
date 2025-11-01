package com.albarmajy.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.albarmajy.todo.data.TaskRepository
import com.albarmajy.todo.ui.TodoScreen
import com.albarmajy.todo.ui.TodoViewModel
import com.albarmajy.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo = TaskRepository(this)
        val viewModel = TodoViewModel(repo)
        viewModel.loadTasks()

        setContent {
            ToDoTheme {
                TodoScreen(viewModel)
            }
        }
    }
}

