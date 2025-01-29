package com.example.pizzashift2025.di

import com.example.pizzashift2025.pizza_main.di.ViewModelPizzaMainModule
import dagger.Module

@Module(
    includes = [
        ViewModelPizzaMainModule::class
    ]
)
interface ViewModelModule {
}