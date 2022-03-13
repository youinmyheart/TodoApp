package com.example.todoapp.repository.network

import com.example.todoapp.model.Customer
import com.example.todoapp.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("call")
    fun getCallList() : Call<List<Customer>>

    @GET("buy")
    fun getBuyList(): Call<List<Product>>
}