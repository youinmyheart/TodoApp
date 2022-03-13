package com.example.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Customer
import com.example.todoapp.repository.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerListViewModel(val app: Application): AndroidViewModel(app) {

    private lateinit var mainActionListener: MainActionListener

    fun getMainActionListener() = mainActionListener

    fun setMainActionListener(listener: MainActionListener) {
        mainActionListener = listener
    }

    interface MainActionListener {
        fun onTapCallList()
        fun onTapBuyList()
        fun onTapSellList()
    }

    companion object {
        private val TAG: String = CustomerListViewModel::class.java.simpleName
    }

    private val customersLive = MutableLiveData<List<Customer>?>(emptyList())

    fun getCustomersLive(): LiveData<List<Customer>?> = customersLive

    fun getCallList() {
        Api.getCallList(app, object : Callback<List<Customer>> {
            override fun onResponse(
                call: Call<List<Customer>>,
                response: Response<List<Customer>>
            ) {
                Log.d(TAG, "onResponse")
                customersLive.value = response.body()
                response.body()?.forEach { item ->
                    Log.d(TAG, "item: $item")
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                customersLive.value = emptyList()
            }
        })
    }
}