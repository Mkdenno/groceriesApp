package com.example.groceriesshop.presentation.item_detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.groceriesshop.presentation.home.Groceries

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GroceryDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    grocery: Groceries
) {
    
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = grocery.img),
            contentDescription = grocery.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "image/${grocery.img}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 400)
                    }
                ),
            contentScale = ContentScale.FillBounds
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                text = grocery.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ksh ${grocery.price}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = ", ${grocery.quantity}"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = .5.dp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = grocery.description,
                color = MaterialTheme.colorScheme.onBackground.copy(.5f)
            )
        }
    }
}