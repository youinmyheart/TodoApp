package com.example.todoapp

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class BindableViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    private val weakBinding = WeakReference(binding)

    abstract fun bind(item: Any)

    internal fun getBinding() = weakBinding.get()
}