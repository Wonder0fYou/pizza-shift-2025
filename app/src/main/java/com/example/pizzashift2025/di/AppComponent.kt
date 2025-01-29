package com.example.pizzashift2025.di

import android.app.Application
import android.content.Context
import com.example.pizzashift2025.MainActivity
import com.example.pizzashift2025.PizzaApplication
import com.example.pizzashift2025.navigation.di.NavigationModule
import com.example.pizzashift2025.pizza_main.di.NetworkPizzaMainModule
import com.example.pizzashift2025.pizza_main.di.RepositoryPizzaMainModule
import com.example.pizzashift2025.pizza_main.di.ViewModelPizzaMainModule
import com.example.pizzashift2025.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        NetworkPizzaMainModule::class,
        RepositoryPizzaMainModule::class,
        NavigationModule::class,
        ViewModelPizzaMainModule::class
    ]
)
@ApplicationScope
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application,
            @BindsInstance context: Context
        ): AppComponent
    }
    fun inject(app: PizzaApplication)
    fun inject(activity: MainActivity)
}