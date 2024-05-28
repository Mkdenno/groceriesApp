package com.example.groceriesshop.mappers

import Cart
import com.example.groceriesshop.presentation.home.Groceries

fun Groceries.toCart():Cart{
    return Cart(id =0 , img, name, price, quantity, description, count = 0)
}