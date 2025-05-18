package com.mobile.jetpack_compose_assignment_2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(viewModel: TodoDetailViewModel, onBack: () -> Unit) {
    val todo by viewModel.todo.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Todo Detail") }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }) { padding ->
        todo?.let {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Title: ${it.title}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Status: ${if (it.completed) "Completed" else "Incomplete"}")
            }
        } ?: Text("Loading...", modifier = Modifier.padding(padding).padding(16.dp))
    }
}