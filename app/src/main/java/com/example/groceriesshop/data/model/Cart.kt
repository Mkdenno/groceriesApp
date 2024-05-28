package com.example.groceriesshop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val img: Int,
    val name: String,
    val price: String,
    val quantity: String,
    val description: String,
    val count: Int = 0
)



