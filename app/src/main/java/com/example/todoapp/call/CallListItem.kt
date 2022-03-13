package com.example.todoapp.call

import androidx.databinding.ViewDataBinding
import com.example.todoapp.*
import com.example.todoapp.model.Customer

class CallListItem(val customer: Customer,
                   override val adapter: BindableRecyclerAdapter<BindableViewHolder>) : SimpleRecyclerAdapter.SimpleRecyclerItem {

    override fun getLayout(): Int = R.layout.customer_item

    override fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder = {
        CallListItemViewHolder(it)
    }

    private inner class CallListItemViewHolder(val binding: ViewDataBinding) : BindableViewHolder(binding) {

        override fun bind(item: Any) {
            getBinding()?.let { binding ->
                when (item) {
                    is CallListItem -> {
                        binding.setVariable(BR.customer, item.customer)
                    }
                    else -> {}
                }
            }
            binding.executePendingBindings()
        }
    }
}