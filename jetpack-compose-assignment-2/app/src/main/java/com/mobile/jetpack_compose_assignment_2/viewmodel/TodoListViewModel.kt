package com.mobile.jetpack_compose_assignment_2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.jetpack_compose_assignment_2.data.model.Todo
import com.mobile.jetpack_compose_assignment_2.repository.TodoRepository

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            try {
                _todos.value = repository.getTodos()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.toString()
            }
        }
    }
}
