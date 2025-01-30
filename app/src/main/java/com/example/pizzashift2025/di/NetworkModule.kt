package com.example.pizzashift2025.di

import com.example.pizzashift2025.shared.pizza.di.NetworkPizzaMainModule
import com.example.pizzashift2025.util.ApplicationScope
import com.example.pizzashift2025.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(
    includes = [
        NetworkPizzaMainModule::class,
    ]
)
interface NetworkModule {
    companion object {

        @Provides
        @ApplicationScope
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @ApplicationScope
        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}