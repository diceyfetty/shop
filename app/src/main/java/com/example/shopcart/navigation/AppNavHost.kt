package com.example.shopcart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopcart.data.ProductViewModel
import com.example.shopcart.ui.theme.SplashScreen
import com.example.shopcart.ui.theme.screens.home.DashboardScreen
import com.example.shopcart.ui.theme.screens.login.LoginScreen
//import com.example.shopcart.ui.theme.screens.products.AddProductScreen
import com.example.shopcart.ui.theme.screens.products.EditProductScreen
//import com.example.shopcart.ui.theme.screens.products.UpdateProductScreen
//import com.example.shopcart.ui.theme.screens.products.ViewProductsScreen
import com.example.shopcart.ui.theme.screens.profile.ProfileScreen
import com.example.shopcart.ui.theme.screens.register.RegisterScreen
import com.example.shopcart.ui.theme.screens.settings.SettingsScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH) {
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
        composable(ROUTE_DASHBOARD) { DashboardScreen(navController) }
//        composable(ROUTE_ADD_PRODUCT) { AddProductScreen(navController) }
//        composable(ROUTE_VIEW_PRODUCTS) { ViewProductsScreen(navController) }
//        composable(ROUTE_EDIT_PRODUCT) { EditProductScreen(navController) }
//        composable(
//            route = "${ROUTE_UPDATE_PRODUCT}/{productId}",
//            arguments = listOf(navArgument("productId") { type = NavType.StringType }))
////        ) { backStackEntry ->
////            val productId = backStackEntry.arguments?.getString("productId") ?: ""
////            UpdateProductScreen(
////                navController = navController,
////                productId = productId,
////                viewModel = ProductViewModel()
////            )
        }
    }
//}