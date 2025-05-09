package com.example.shopcart.ui.theme.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopcart.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onNavigateToCart: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToAccount: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("QuickCart") },
                actions = {
                    IconButton(onClick = { /* search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Category") },
                    selected = false,
                    onClick = onNavigateToCategory
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("Cart") },
                    selected = false,
                    onClick = onNavigateToCart
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Account") },
                    selected = false,
                    onClick = onNavigateToAccount
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                CategoryTabs()
                Spacer(modifier = Modifier.height(16.dp))
                BannerSection()
                Spacer(modifier = Modifier.height(16.dp))
                FlashSaleSection()
                Spacer(modifier = Modifier.height(16.dp))
                WhatsNewSection()
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun CategoryTabs() {
    val categories = listOf("Hot", "TVs", "Appliances", "Kitchen", "Home", "Phones")
    ScrollableTabRow(selectedTabIndex = 0) {
        categories.forEachIndexed { index, title ->
            Tab(selected = index == 0, onClick = {}) {
                Text(title, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun BannerSection() {
    Image(
        painter = painterResource(id = R.drawable.banner),
        contentDescription = "Banner",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    )
}

@Composable
fun FlashSaleSection() {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Flash Sale", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Ends in 00:49:27", color = Color.Red)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(5) {
                ProductCard("KSh 999", R.drawable.flash_item1)
            }
        }
    }
}

@Composable
fun WhatsNewSection() {
    Column(Modifier.padding(16.dp)) {
        Text("What's New", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(4) {
                ProductCard("KSh 1599", R.drawable.new_item1)
            }
        }
    }
}

@Composable
fun ProductCard(price: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(price, color = Color.Red, fontWeight = FontWeight.Bold)
    }
}