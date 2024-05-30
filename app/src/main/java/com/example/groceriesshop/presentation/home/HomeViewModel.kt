package com.example.groceriesshop.presentation.home

import com.example.groceriesshop.data.model.Cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceriesshop.data.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartItemCount = cartRepository.cartItemCount.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        0
    )

    fun addToCart(cart: Cart) {
        viewModelScope.launch {
            cartRepository.addCart(cart)
        }
    }
}