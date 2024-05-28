package com.example.groceriesshop.di

import android.app.Application
import androidx.room.Room
import com.example.groceriesshop.data.CartDatabase
import com.example.groceriesshop.data.repository.CartRepository
import com.example.groceriesshop.data.repository.CartRepositoryImpl

interface AppModule {
    val db: CartDatabase
    val cartRepository: CartRepository
}

class AppModuleImpl(
    private val application: Application
) : AppModule {
    override val db: CartDatabase by lazy {
        Room.databaseBuilder(
            application,
            CartDatabase::class.java,
            CartDatabase.DATABASE_NAME
        ).build()
    }
    override val cartRepository: CartRepository by lazy {
        CartRepositoryImpl(db.cartDao)
    }
}