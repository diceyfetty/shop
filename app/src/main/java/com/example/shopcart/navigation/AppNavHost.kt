package com.example.shopcart.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopcart.data.ProductViewModel
import com.example.shopcart.ui.theme.SplashScreen
import com.example.shopcart.ui.theme.screens.home.HomeScreen
import com.example.shopcart.ui.theme.screens.login.LoginScreen
import com.example.shopcart.ui.theme.screens.products.AddProductScreen
import com.example.shopcart.ui.theme.screens.products.EditProductScreen
import com.example.shopcart.ui.theme.screens.products.UpdateProductScreen
import com.example.shopcart.ui.theme.screens.products.ViewProductScreen
import com.example.shopcart.ui.theme.screens.profile.ProfileScreen
import com.example.shopcart.ui.theme.screens.register.RegisterScreen
import com.example.shopcart.ui.theme.screens.settings.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_SPLASH) { inclusive = true }
                }
            }
        }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_PROFILE) { ProfileScreen() }
        composable(ROUTE_SETTINGS) { SettingsScreen() }
        composable(ROUTE_HOME) {
            HomeScreen(
                navController = navController,
                onNavigateToCart = { navController.navigate(ROUTE_CART) },
                onNavigateToCategory = { navController.navigate(ROUTE_CATEGORY) },
                onNavigateToAccount = { navController.navigate(ROUTE_PROFILE) }
            )
        }
        composable(ROUTE_ADD_PRODUCT) { AddProductScreen(navController) }
        composable(
            route = "$ROUTE_VIEW_PRODUCT/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val viewModel: ProductViewModel = viewModel(backStackEntry)
            ViewProductScreen(
                navController = navController,
                productId = productId,
                viewModel = viewModel
            )
        }
        composable(
            route = "$ROUTE_EDIT_PRODUCT/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val productViewModel: ProductViewModel = viewModel(backStackEntry)
            EditProductScreen(
                navController = navController,
                productId = productId,
                productViewModel = productViewModel
            )
        }

        composable(
            route = "$ROUTE_UPDATE_PRODUCT/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val viewModel: ProductViewModel = viewModel(backStackEntry)
            UpdateProductScreen(
                navController = navController,
                productId = productId,
                viewModel = viewModel
            )
        }
    }
}
