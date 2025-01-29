package com.example.pizzashift2025.pizza_main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzashift2025.pizza_main.presentation.MainListRoute
import com.example.pizzashift2025.pizza_main.presentation.MainViewModel
import com.example.pizzashift2025.utils.getViewModel

@Composable
fun PizzaMainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = getViewModel()
    val state by mainViewModel.state.collectAsStateWithLifecycle()
    Surface {
        Column {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = MainListRoute
            ) {
                composable<MainListRoute> {
                    PizzaListScreen(
                        viewModel = getViewModel()
                    )
                }
            }
        }
    }
}