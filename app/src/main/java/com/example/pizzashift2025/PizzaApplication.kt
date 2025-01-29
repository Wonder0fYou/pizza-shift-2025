package com.example.pizzashift2025

import android.app.Application
import com.example.pizzashift2025.di.AppComponent
import com.example.pizzashift2025.di.DaggerAppComponent

class PizzaApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(
            this, applicationContext
        )
        appComponent.inject(this)
    }
}