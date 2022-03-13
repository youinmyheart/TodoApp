package com.example.todoapp

import androidx.databinding.ViewDataBinding

class SimpleRecyclerAdapter: BindableRecyclerAdapter<BindableViewHolder>() {

    private var items: MutableList<SimpleRecyclerItem> = mutableListOf()
    private val viewTypesAndHolders: MutableMap<Int, (binding: ViewDataBinding) -> BindableViewHolder> = mutableMapOf()

    override fun getItem(position: Int) = getAllItems()[position]

    override fun getLayout(position: Int) = getItem(position).getLayout()

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int) = viewTypesAndHolders[viewType]?.invoke(binding)

    override fun getItemCount() = getAllItems().size

    fun setItems(items: List<SimpleRecyclerItem>) {
        this.items = items.toMutableList()
        items.forEach {
            viewTypesAndHolders[it.getViewType()] = it.getViewHolderProvider()
        }
        notifyDataSetChanged()
    }

    fun addItem(item: SimpleRecyclerItem, index: Int = itemCount) {
        items.add(index, item)
        viewTypesAndHolders[item.getViewType()] = item.getViewHolderProvider()
        notifyDataSetChanged()
    }

    fun getAllItems(): List<SimpleRecyclerItem> = items

    interface SimpleRecyclerItem {
        val adapter: BindableRecyclerAdapter<BindableViewHolder>

        fun getLayout(): Int
        fun getViewType() = getLayout()
        fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder
    }
}