package com.example.pizzashift2025.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.pizzashift2025.di.LocalViewModelFactory

@Composable
inline fun <reified VM: ViewModel> getViewModel(): VM {
    val factory = LocalViewModelFactory.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner"
    }
    return remember(VM::class) {
        ViewModelProvider(viewModelStoreOwner, factory)[VM::class.java]
    }
}