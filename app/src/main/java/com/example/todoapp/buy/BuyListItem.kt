package com.example.todoapp.buy

import androidx.databinding.ViewDataBinding
import com.example.todoapp.*
import com.example.todoapp.model.Product

class BuyListItem(val product: Product,
                  override val adapter: BindableRecyclerAdapter<BindableViewHolder>) : SimpleRecyclerAdapter.SimpleRecyclerItem {

    override fun getLayout() = R.layout.product_item

    override fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder = {
        BuyListItemViewHolder(it)
    }

    private inner class BuyListItemViewHolder(val binding: ViewDataBinding) : BindableViewHolder(binding) {

        override fun bind(item: Any) {
            getBinding()?.let { binding ->
                when (item) {
                    is BuyListItem -> {
                        binding.setVariable(BR.product, item.product)
                    }
                    else -> {}
                }
            }
            binding.executePendingBindings()
        }
    }
}