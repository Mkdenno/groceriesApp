package com.example.groceriesshop.presentation.cart

import android.util.Log
import com.example.groceriesshop.data.model.Cart
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceriesshop.data.repository.ItemCount

@Composable
fun CartScreen(
    cartViewModel: CartViewModel
) {

    val cartList by cartViewModel.cartList.collectAsState()

    Log.i("items***", cartList.toString())

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cartList) { item ->
            GroceryItem(
                cart = item,
                onClickSubtract = { cartViewModel.updateItemCount(ItemCount.DECREMENT, item.id) },
                onClickAdd = { cartViewModel.updateItemCount(ItemCount.INCREMENT, item.id) }
            )
        }
    }
}

@Composable
fun GroceryItem(
    modifier: Modifier = Modifier,
    cart: Cart,
    onClickSubtract: () -> Unit,
    onClickAdd: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = cart.img),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = cart.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = cart.quantity,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = cart.price,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Items: ${cart.count}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column {

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickAdd()
                    }
                    .background(
                        color = Color.Red.copy(.5f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "-")
            }
            Text(text = cart.quantity)

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickSubtract()
                    }
                    .background(
                        color = Color.Green.copy(.5f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+")
            }
        }
    }
}