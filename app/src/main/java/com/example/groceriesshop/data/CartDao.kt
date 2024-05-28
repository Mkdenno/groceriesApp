package com.example.groceriesshop.data

import com.example.groceriesshop.data.model.Cart
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getAllCart(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cart: Cart)

    @Query("UPDATE cart SET count=:count WHERE id=:id")
    suspend fun updateCount(count: Int, id: Int)

    @Query("SELECT * FROM cart WHERE id=:id")
    fun getCartItem(id: Int): Flow<Cart>

    @Delete
    suspend fun deleteCart(cart: Cart)
}