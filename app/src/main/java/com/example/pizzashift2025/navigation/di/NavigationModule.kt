package com.example.pizzashift2025.navigation.di

import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.navigation.GlobalRouterImpl
import com.example.pizzashift2025.navigation.featureRoute.MainRouterImpl
import com.example.pizzashift2025.pizza_main.presentation.MainRouter
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    fun provideGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter {
        return globalRouterImpl
    }

    @Provides
    fun provideMainRoute(mainRouterImpl: MainRouterImpl): MainRouter {
        return mainRouterImpl
    }
}