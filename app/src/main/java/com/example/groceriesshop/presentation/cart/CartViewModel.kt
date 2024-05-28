package com.example.groceriesshop.presentation.cart

import Cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceriesshop.data.repository.CartRepository
import com.example.groceriesshop.data.repository.ItemCount
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
):ViewModel() {
    val cartList=cartRepository.getAllCart().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        emptyList()
    )

    fun updateItemCount(itemCount: ItemCount, id:Int){
        viewModelScope.launch {
            cartRepository.updateCount(id, itemCount)
        }

    }

    fun deleteItem(cart: Cart){
        viewModelScope.launch {
            cartRepository.deleteCart(cart)
        }
    }
}