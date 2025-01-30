package com.example.pizzashift2025.navigation.di

import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.navigation.GlobalRouterImpl
import com.example.pizzashift2025.navigation.featureRoute.MainListRouterImpl
import com.example.pizzashift2025.navigation.featureRoute.MainRouterImpl
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.MainListRouter
import com.example.pizzashift2025.presentation.MainRouter
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    fun provideGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter {
        return globalRouterImpl
    }

    @Provides
    fun provideListMainRoute(mainListRouterImpl: MainListRouterImpl): MainListRouter {
        return mainListRouterImpl
    }

    @Provides
    fun provideMainRoute(mainRouterImpl: MainRouterImpl): MainRouter {
        return mainRouterImpl
    }
}