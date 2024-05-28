package com.example.groceriesshop.data.mappers

import com.example.groceriesshop.data.model.Cart
import com.example.groceriesshop.presentation.home.Groceries

fun Groceries.toCart(): Cart {
    return Cart(id = 0, img, name, price, quantity, description, count = 0)
}