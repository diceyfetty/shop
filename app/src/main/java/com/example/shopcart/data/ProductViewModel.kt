package com.example.shopcart.data

import com.example.shopcart.models.ProductModel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProductViewModel : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("products")

    private val _productList = MutableLiveData<List<ProductModel>>()
    val productList: LiveData<List<ProductModel>> = _productList

    // ---------------- ADD PRODUCT ----------------
    fun addProduct(
        name: String,
        category: String,
        price: String,
        description: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productId = dbRef.push().key ?: return@launch
                val product = ProductModel(
                    name = name,
                    category = category,
                    price = price.toDoubleOrNull() ?: 0.0,
                    description = description,
                    productId = productId
                )
                dbRef.child(productId).setValue(product).await()
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Product added successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack() // or navigate to ROUTE_HOME
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // ---------------- FETCH PRODUCTS ----------------
    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbRef.get().addOnSuccessListener { snapshot ->
                    val products = snapshot.children.mapNotNull {
                        it.getValue(ProductModel::class.java)
                    }
                    _productList.postValue(products)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ---------------- DELETE PRODUCT ----------------
    fun deleteProduct(productId: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbRef.child(productId).removeValue().await()
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
                    fetchProducts() // Refresh list
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to delete product", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // ---------------- UPDATE PRODUCT ----------------
    fun updateProduct(
        productId: String,
        name: String,
        category: String,
        price: String,
        description: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedProduct = ProductModel(
                    name = name,
                    category = category,
                    price = price.toDoubleOrNull() ?: 0.0,
                    description = description,
                    productId = productId
                )
                dbRef.child(productId).setValue(updatedProduct).await()
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Product updated", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Update failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // ---------------- GET PRODUCT BY ID ----------------
    fun getProductById(productId: String, onResult: (ProductModel?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val snapshot = dbRef.child(productId).get().await()
                val product = snapshot.getValue(ProductModel::class.java)
                onResult(product)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
}
