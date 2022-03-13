package com.example.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Product
import com.example.todoapp.repository.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel(val app: Application): AndroidViewModel(app) {

    companion object {
        private val TAG: String = ProductListViewModel::class.java.simpleName
    }

    private val productsLive = MutableLiveData<List<Product>?>(emptyList())

    fun getProductsLive(): LiveData<List<Product>?> = productsLive

    fun getBuyList() {
        Api.getBuyList(app, object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                Log.d(TAG, "onResponse")
                productsLive.value = response.body()
                response.body()?.forEach { item ->
                    Log.d(TAG, "item: $item")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                productsLive.value = emptyList()
            }
        })
    }
}