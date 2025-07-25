package com.mobile.jetpack_compose_assignment_2.repository

import com.mobile.jetpack_compose_assignment_2.data.model.Todo
import com.mobile.jetpack_compose_assignment_2.data.remote.TodoApi
import com.mobile.jetpack_compose_assignment_2.data.local.TodoDao
import kotlinx.coroutines.flow.first

class TodoRepository(
    private val api: TodoApi,
    private val dao: TodoDao
) {
    suspend fun getTodos(): List<Todo> {
        return try {
            val todos = api.getTodos()
            dao.insertAll(todos)
            todos
        } catch (e: Exception) {
            e.printStackTrace()

            // Collect the flow from Room
            dao.getAllTodos().first() // requires: import kotlinx.coroutines.flow.first
        }
    }

    suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }
}

