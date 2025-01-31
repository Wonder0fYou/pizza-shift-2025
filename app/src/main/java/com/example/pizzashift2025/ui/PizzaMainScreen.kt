package com.example.pizzashift2025.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizzashift2025.R
import com.example.pizzashift2025.feature.pizza_details.PizzaDetailsRoute
import com.example.pizzashift2025.feature.pizza_details.ui.PizzaDetailsScreen
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.MainListRoute
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.ui.PizzaListScreen
import com.example.pizzashift2025.presentation.MainViewModel
import com.example.pizzashift2025.presentation.NavigationOption
import com.example.pizzashift2025.util.getViewModel

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
                composable<PizzaDetailsRoute> {
                    val destination = it.toRoute<PizzaDetailsRoute>()
                    val pizzaId = destination.pizzaId
                    PizzaDetailsScreen(
                        viewModel = getViewModel(),
                        pizzaId = pizzaId
                    )
                }
            }
            
            BottomNavigation(
                navigationOption = state.navigationOption,
                selectedNavigationOption = state.selectedNavOption,
                onItemClicked = mainViewModel::openOption
            )
        }
    }
}

@Composable
private fun BottomNavigation(
    navigationOption: List<NavigationOption>,
    selectedNavigationOption: NavigationOption?,
    onItemClicked: (NavigationOption) -> Unit
) {
    NavigationBar {
        for(option in navigationOption) {
            NavigationBarItem(
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = { Icon(imageVector = navOptionIcon(option), contentDescription = "") },
                label = { Text(text = navOptionLabel(option = option))})
        }
    }
}

private fun navOptionIcon(option: NavigationOption): ImageVector =
    when(option){
        NavigationOption.CATALOG -> Icons.Default.List
    }

@Composable
private fun navOptionLabel(option: NavigationOption): String = stringResource(
    when(option) {
        NavigationOption.CATALOG -> R.string.pizza
    }
)