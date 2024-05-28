package com.example.groceriesshop.data.repository

import com.example.groceriesshop.data.model.Cart
import com.example.groceriesshop.data.CartDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

enum class ItemCount{
    INCREMENT,
    DECREMENT
}
interface CartRepository {


    fun getAllCart(): Flow<List<Cart>>

    suspend fun addCart(cart: Cart)


    suspend fun updateCount( id:Int, itemCount: ItemCount)



    suspend fun deleteCart(cart: Cart)
}

class CartRepositoryImpl(

   private val dao:CartDao
):CartRepository {
    override fun getAllCart(): Flow<List<Cart>> {
        return dao.getAllCart()
    }

    override suspend fun addCart(cart: Cart) {
        dao.addCart(cart)
    }

    override suspend fun updateCount( id: Int, itemCount: ItemCount) {
        val cartItem=dao.getCartItem(id).first()
        var count=cartItem.count
        if (cartItem.count>=0){

            when(itemCount){
                ItemCount.INCREMENT->{
                    count++
                }
                ItemCount.DECREMENT->{
                    count--
                }
            }

            dao.updateCount(count,id)

        }

    }


    override suspend fun deleteCart(cart: Cart) {
        dao.deleteCart(cart)
    }
}