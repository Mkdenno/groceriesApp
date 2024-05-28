package com.example.groceriesshop.presentation.home

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceriesshop.R
import com.example.groceriesshop.mappers.toCart


val dummyGroceries = listOf(
    Groceries(
        R.drawable.g1,
        "Onion",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g2,
        "Potato",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g3,
        "Tomato",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g4,
        "HoHo",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g5,
        "Saumu",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g6,
        "Ginger",
        "200",
        "1kg",
        "They are very delicious"
    ),

    Groceries(
        R.drawable.g7,
        "Avocado",
        "200",
        "1kg",
        "They are very delicious"
    ),
    Groceries(
        R.drawable.g8,
        "Banana",
        "200",
        "1kg",
        "They are very delicious"
    ),

    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToCartScreen:()->Unit,
    homeViewModel: HomeViewModel
    ) {




    Scaffold (
        topBar = {
            TopAppBar(title = { /*TODO*/ },
                actions = {
                    IconButton(onClick = { onNavigateToCartScreen() }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription =null )
                    }
                }
                )
        },
                floatingActionButton = {
            FloatingActionButton(onClick = {
//                navigateToAddScreen()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription =null )

            }
        }


    ){
        LazyColumn (
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            items(dummyGroceries){item ->
                HomeItems(groceries = item, onItemClick = {
                    homeViewModel.addToCart(item.toCart())
                })
            }


        }
    }

}

@Composable
fun HomeItems(
    modifier: Modifier = Modifier,
    groceries: Groceries,
    onItemClick:()->Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = groceries.img),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
        ) {
            Text(
                text = groceries.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = groceries.quantity,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = groceries.price,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Column {

            Text(text = groceries.quantity)
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Add To CArt",
                    color = Color.Green.copy(.5f),
                    modifier = Modifier
                        .clickable {
                            onItemClick()
                        }
                    )

            }



        }


    }
}



data class Groceries(
    val img: Int,
    val name: String,
    val price: String,
    val quantity: String,
    val description: String
)