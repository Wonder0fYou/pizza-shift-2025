package com.example.pizzashift2025.pizza_main.di

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.util.ViewModelKey
import com.example.pizzashift2025.pizza_main.presentation.MainViewModel
import com.example.pizzashift2025.pizza_main.presentation.PizzaListMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelPizzaMainModule {
    @Binds
    @IntoMap
    @ViewModelKey(PizzaListMainViewModel::class)
    fun bindViewModelPizzaListMain(viewModel: PizzaListMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindViewModelMain(viewModel: MainViewModel): ViewModel
}