package com.example.pizzashift2025.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizzashift2025.R
import com.example.pizzashift2025.feature.pizza_details.PizzaDetailsRoute
import com.example.pizzashift2025.feature.pizza_details.ui.PizzaDetailsScreen
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.MainListRoute
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.ui.PizzaListScreen
import com.example.pizzashift2025.navigation.GlobalRouterImpl
import com.example.pizzashift2025.presentation.MainViewModel
import com.example.pizzashift2025.presentation.NavigationOption
import com.example.pizzashift2025.util.getViewModel

@Composable
fun PizzaMainScreen(
    globalRouter: GlobalRouterImpl
) {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = getViewModel()
    val state by mainViewModel.state.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val openedOption = state.navigationOption.firstOrNull { destination.hasRoute(it.route) }
            mainViewModel.handleOpenedScreen(openedOption)
        }

        globalRouter.setNavController(navController)
        onDispose {
            globalRouter.clearNavController()
        }
    }
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

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black.copy(alpha = 0.12f)
            )
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
        for (option in navigationOption) {
            NavigationBarItem(
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = {
                    Icon(
                        imageVector = navOptionIcon(option),
                        contentDescription = "",
                        tint = if (selectedNavigationOption == option)
                            MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
                    )
                },
                label = {
                    Text(
                        text = navOptionLabel(option = option),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selectedNavigationOption == option)
                            MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
                    )
                })
        }
    }
}

private fun navOptionIcon(option: NavigationOption): ImageVector =
    when (option) {
        NavigationOption.CATALOG -> Icons.Default.List
        NavigationOption.ORDERS -> Icons.Default.CheckCircle
        NavigationOption.CART -> Icons.Default.ShoppingCart
        NavigationOption.PROFILE -> Icons.Default.AccountBox
    }

@Composable
private fun navOptionLabel(option: NavigationOption): String = stringResource(
    when (option) {
        NavigationOption.CATALOG -> R.string.pizza
        NavigationOption.ORDERS -> R.string.orders
        NavigationOption.CART -> R.string.cart
        NavigationOption.PROFILE -> R.string.profile
    }
)