package com.example.practice

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val loginViewmodel: LoginViewmodel = viewModel()
            val username = loginViewmodel.username.value

            LoginScreen(
                loginViewmodel = loginViewmodel,
                onNavigate = {
                    if (username.isNotBlank()) {
                        navController.navigate("welcome/$username")
                    }
                }
            )
        }

        composable(
            route = "welcome/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val passedUsername = backStackEntry.arguments?.getString("username") ?: ""
            Welcome(username = passedUsername)
        }
    }
}