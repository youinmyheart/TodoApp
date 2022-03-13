package com.example.todoapp

import android.content.Context
import com.example.todoapp.model.Customer
import com.example.todoapp.model.Product

object UIUtil {
    @JvmStatic
    fun getFormattedName(context: Context, customer: Customer) = context.getString(R.string.name, customer.name)

    @JvmStatic
    fun getFormattedNumber(context: Context, customer: Customer) = context.getString(R.string.number, customer.number)

    @JvmStatic
    fun getFormattedProductName(context: Context, product: Product) = context.getString(R.string.name, product.name)

    @JvmStatic
    fun getFormattedProductPrice(context: Context, product: Product) = context.getString(R.string.price, product.price)

    @JvmStatic
    fun getFormattedProductQuantity(context: Context, product: Product) = context.getString(R.string.quantity, product.quantity)
}