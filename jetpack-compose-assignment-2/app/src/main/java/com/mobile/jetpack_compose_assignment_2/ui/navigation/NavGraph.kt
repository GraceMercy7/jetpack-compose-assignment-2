package com.mobile.jetpack_compose_assignment_2.ui.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.mobile.jetpack_compose_assignment_2.ui.screen.TodoDetailScreen
import com.mobile.jetpack_compose_assignment_2.ui.screen.TodoListScreen
import com.mobile.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel
import com.mobile.jetpack_compose_assignment_2.viewmodel.TodoListViewModel


@Composable
fun NavGraph(
    listViewModel: TodoListViewModel,
    detailViewModelFactory: (Int) -> TodoDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "todo_list") {
        composable("todo_list") {
            TodoListScreen(viewModel = listViewModel) {
                navController.navigate("todo_detail/$it")
            }
        }
        composable(
            "todo_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")!!
            TodoDetailScreen(viewModel = detailViewModelFactory(id)) {
                navController.popBackStack()
            }
        }
    }
}
