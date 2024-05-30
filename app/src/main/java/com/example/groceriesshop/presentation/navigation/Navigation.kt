package com.example.groceriesshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.groceriesshop.CartApp
import com.example.groceriesshop.presentation.cart.CartScreen
import com.example.groceriesshop.presentation.cart.CartViewModel
import com.example.groceriesshop.presentation.home.Groceries
import com.example.groceriesshop.presentation.home.HomeScreen
import com.example.groceriesshop.presentation.home.HomeViewModel
import com.example.groceriesshop.presentation.item_detail.GroceryDetailScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen) {
        composable<Screen.HomeScreen>{
            val viewModel = viewModel<HomeViewModel>(
                factory = viewModelFactoryHelper {
                    HomeViewModel(CartApp.appModule.cartRepository)
                }
            )
            HomeScreen(
                onNavigateToCartScreen = {
                    navController.navigate(Screen.CartScreen)
                },
                homeViewModel = viewModel,
                onNavigateToDetailsScreen = {
                    navController.navigate(
                        Screen.GroceryDetailsScreen(
                            it.img,
                            it.name,
                            it.price,
                            it.quantity,
                            it.description
                        )
                    )
                }
            )
        }
        
        composable<Screen.GroceryDetailsScreen> {
            val args = it.toRoute<Screen.GroceryDetailsScreen>()
            GroceryDetailScreen(
                grocery = Groceries(
                    args.img,
                    args.name,
                    args.price,
                    args.quantity,
                    args.description
                )
            )
        }

        composable<Screen.CartScreen>{
            val viewModel = viewModel<CartViewModel>(
                factory = viewModelFactoryHelper {
                    CartViewModel(
                        CartApp.appModule.cartRepository
                    )
                }
            )

            CartScreen(cartViewModel = viewModel)
        }
    }
}

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()
    @Serializable
    data class GroceryDetailsScreen(
        val img: Int,
        val name: String,
        val price: String,
        val quantity: String,
        val description: String
    ): Screen()
    @Serializable
    data object CartScreen : Screen()
}

fun <VM : ViewModel> viewModelFactoryHelper(initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}