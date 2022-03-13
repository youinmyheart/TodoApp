package com.example.todoapp.repository

import androidx.annotation.WorkerThread
import com.example.todoapp.model.Product
import com.example.todoapp.repository.db.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: Flow<List<Product>> = productDao.getProducts()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(product: Product) {
        productDao.insert(product)
    }
}