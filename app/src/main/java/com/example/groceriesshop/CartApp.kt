package com.example.groceriesshop

import android.app.Application
import com.example.groceriesshop.di.AppModule
import com.example.groceriesshop.di.AppModuleImpl

class CartApp:Application() {

    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()

        appModule= AppModuleImpl(this)
    }
}