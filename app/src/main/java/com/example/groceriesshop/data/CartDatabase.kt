package com.example.groceriesshop.data

import com.example.groceriesshop.data.model.Cart
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Cart::class],
    exportSchema = false
)
abstract class CartDatabase : RoomDatabase() {

    abstract val cartDao: CartDao

    companion object {
        const val DATABASE_NAME = "cartydp"
    }
}