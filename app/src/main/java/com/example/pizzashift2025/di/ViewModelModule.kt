package com.example.pizzashift2025.di

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.feature.pizza_details.di.ViewModelPizzaDetailsModule
import com.example.pizzashift2025.presentation.MainViewModel
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.di.ViewModelPizzaMainModule
import com.example.pizzashift2025.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelPizzaMainModule::class,
        ViewModelPizzaDetailsModule::class
    ]
)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindViewModelMain(viewModel: MainViewModel): ViewModel
}