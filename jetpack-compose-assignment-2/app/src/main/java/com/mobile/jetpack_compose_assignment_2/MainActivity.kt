package com.mobile.jetpack_compose_assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mobile.jetpack_compose_assignment_2.data.local.TodoDatabase
import com.mobile.jetpack_compose_assignment_2.data.remote.TodoApi
import com.mobile.jetpack_compose_assignment_2.repository.TodoRepository
import com.mobile.jetpack_compose_assignment_2.ui.navigation.NavGraph
import com.mobile.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel
import com.mobile.jetpack_compose_assignment_2.viewmodel.TodoListViewModel

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TodoDatabase.getDatabase(this)
        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(TodoApi::class.java)
        print(api)

        val repo = TodoRepository(api, db.todoDao())
        val listViewModel = TodoListViewModel(repo)

        setContent {
            NavGraph(
                listViewModel = listViewModel,
                detailViewModelFactory = { TodoDetailViewModel(repo, it) }
            )
        }
    }
}
