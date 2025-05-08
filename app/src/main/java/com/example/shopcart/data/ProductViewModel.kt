package com.example.shopcart.data

import com.example.shopcart.models.ProductModel
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProductViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance().collection("Products")

    fun addProduct(
        name: String,
        category: String,
        price: String,
        description: String,
        navController: NavController,
        context: Context
    ) {
        if (name.isBlank() || category.isBlank() || price.isBlank() || description.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val productId = db.document().id
        val product = ProductModel(productId, name, category, price, description)

        db.document(productId).set(product).addOnSuccessListener {
            Toast.makeText(context, "Product added", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_VIEW_PRODUCTS)
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to add product", Toast.LENGTH_SHORT).show()
        }
    }

    fun getProducts(
        product: MutableState<ProductModel>,
        productList: SnapshotStateList<ProductModel>,
        context: Context
    ): SnapshotStateList<ProductModel> {
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Toast.makeText(context, "Error fetching products", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            if (snapshot != null) {
                productList.clear()
                for (doc in snapshot.documents) {
                    val item = doc.toObject(ProductModel::class.java)
                    item?.let { productList.add(it) }
                }
                if (productList.isNotEmpty()) {
                    product.value = productList.first()
                }
            }
        }
        return productList
    }

    fun updateProduct(
        productId: String,
        name: String,
        category: String,
        price: String,
        description: String,
        context: Context,
        navController: NavController
    ) {
        val updatedProduct = ProductModel(productId, name, category, price, description)

        db.document(productId).set(updatedProduct).addOnSuccessListener {
            Toast.makeText(context, "Product updated", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_VIEW_PRODUCTS)
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to update product", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteProduct(
        productId: String,
        context: Context,
        navController: NavController
    ) {
        db.document(productId).delete().addOnSuccessListener {
            Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to delete product", Toast.LENGTH_SHORT).show()
        }
    }
    fun viewProducts(
        product: MutableState<ProductModel>,
        products: SnapshotStateList<ProductModel>,
        context: Context
    ): SnapshotStateList<ProductModel> {
        val ref = FirebaseDatabase.getInstance().getReference("Products")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                products.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(ProductModel::class.java)
                    value?.let {
                        products.add(it)
                    }
                }
                if (products.isNotEmpty()) {
                    product.value = products.first()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch products: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

        return products
    }
}