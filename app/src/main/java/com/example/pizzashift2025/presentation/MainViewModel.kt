package com.example.pizzashift2025.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter
) : ViewModel() {

    private val _state = MutableStateFlow(
        MainState(
            NavigationOption.entries,
            NavigationOption.CATALOG
        )
    )

    fun openOption(option: NavigationOption) {
        _state.value = _state.value.copy(selectedNavOption = option)
        when (option) {
            NavigationOption.CATALOG -> router.openListPizza()
            NavigationOption.ORDERS -> {

            }
            NavigationOption.CART -> {

            }
            NavigationOption.PROFILE -> {

            }
        }
    }

    fun handleOpenedScreen(option: NavigationOption?) {
        _state.value = _state.value.copy(selectedNavOption = option)
    }

    val state: StateFlow<MainState> = _state
}