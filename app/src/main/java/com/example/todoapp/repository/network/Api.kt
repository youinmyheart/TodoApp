package com.example.todoapp.repository.network

import android.content.Context
import android.util.Log
import com.example.todoapp.R
import com.example.todoapp.model.Customer
import com.example.todoapp.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private val TAG: String = Api::class.java.simpleName
    private lateinit var baseUrl: String

    private fun create(context: Context): ApiInterface {
        baseUrl = context.getString(R.string.base_url)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    fun getCallList(context: Context, callback: Callback<List<Customer>>) {
        val call = create(context).getCallList()
        call.enqueue(callback)
    }

    fun getBuyList(context: Context, callback: Callback<List<Product>>) {
        val call = create(context).getBuyList()
        call.enqueue(callback)
    }
}