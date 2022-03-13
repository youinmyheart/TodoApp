package com.example.todoapp.viewmodel

import androidx.lifecycle.*
import com.example.todoapp.model.Product
import com.example.todoapp.repository.ProductRepository
import kotlinx.coroutines.launch

class SellListViewModel(val repository: ProductRepository): ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }
}

class SellListViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SellListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}