package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindableRecyclerAdapter<T : BindableViewHolder> : RecyclerView.Adapter<T>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

        try {
            return getViewHolder(binding, viewType)!!
        } catch (e: IllegalStateException) {
            throw IllegalStateException(
                "The top level element in your xml layout MUST be <layout>!",
                e
            )
        }
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayout(position)
    }

    internal abstract fun getItem(position: Int): Any
    internal abstract fun getLayout(position: Int): Int
    internal abstract fun getViewHolder(binding: ViewDataBinding, viewType: Int): T?
}