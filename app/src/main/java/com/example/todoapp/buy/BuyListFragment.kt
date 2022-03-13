package com.example.todoapp.buy

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.BindableFragment
import com.example.todoapp.R
import com.example.todoapp.SimpleRecyclerAdapter
import com.example.todoapp.ToolbarHelper
import com.example.todoapp.databinding.BuyListFragmentBinding
import com.example.todoapp.viewmodel.ProductListViewModel


class BuyListFragment: BindableFragment<BuyListFragmentBinding>(), ToolbarHelper {

    private lateinit var viewModel: ProductListViewModel

    override fun getLayoutId() = R.layout.buy_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initToolbar(getString(R.string.buy_list))

        val adapter = SimpleRecyclerAdapter()
        viewModel = ViewModelProviders.of(this)[ProductListViewModel::class.java]
        viewModel.getBuyList()
        viewModel.getProductsLive().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setItems(it.map { product ->
                    BuyListItem(product, adapter)
                })
            }
        }
        getFragmentBinding()?.rvList?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    companion object {
        private val TAG: String = BuyListFragment::class.java.simpleName
        @JvmStatic fun newInstance() = BuyListFragment()
    }

    override fun getAppCompatActivity(): AppCompatActivity = activity as AppCompatActivity

    override fun getToolbar(): Toolbar? = getFragmentBinding()?.header?.toolbar

    override fun getBinding(): ViewDataBinding? = getFragmentBinding()
}