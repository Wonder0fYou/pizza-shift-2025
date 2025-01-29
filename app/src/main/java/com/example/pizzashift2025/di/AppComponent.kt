package com.example.pizzashift2025.di

import android.app.Application
import android.content.Context
import com.example.pizzashift2025.MainActivity
import com.example.pizzashift2025.PizzaApplication
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
interface AppComponent {
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