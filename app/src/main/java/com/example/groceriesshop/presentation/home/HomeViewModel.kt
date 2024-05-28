package com.example.groceriesshop.presentation.home

import Cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceriesshop.data.repository.CartRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cartRepository: CartRepository
):ViewModel() {

    fun addToCart(cart: Cart){
        viewModelScope.launch {
            cartRepository.addCart(cart)
        }
    }
}