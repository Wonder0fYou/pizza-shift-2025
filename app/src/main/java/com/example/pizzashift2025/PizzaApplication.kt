package com.example.pizzashift2025

import android.app.Application
import com.example.pizzashift2025.di.AppComponent

class PizzaApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory.create(
            this, applicationContext
        )
    }
}