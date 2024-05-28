package com.example.groceriesshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.groceriesshop.CartApp
import com.example.groceriesshop.presentation.cart.CartScreen
import com.example.groceriesshop.presentation.cart.CartViewModel
import com.example.groceriesshop.presentation.home.HomeScreen
import com.example.groceriesshop.presentation.home.HomeViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val viewModel = viewModel<HomeViewModel>(
                factory = viewModelFactoryHelper {
                    HomeViewModel(CartApp.appModule.cartRepository)
                }
            )
            HomeScreen(
                onNavigateToCartScreen = {
                    navController.navigate(Screen.CartScreen.route)
                },
                homeViewModel = viewModel
            )
        }

        composable(route = Screen.CartScreen.route) {
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

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object CartScreen : Screen("cart_screen")
}

fun <VM : ViewModel> viewModelFactoryHelper(initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}