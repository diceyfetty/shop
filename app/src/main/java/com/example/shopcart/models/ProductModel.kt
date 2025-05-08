package com.example.shopcart.models

data class ProductModel(
    var name: String = "",
    var category: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var productId: String = ""
)
