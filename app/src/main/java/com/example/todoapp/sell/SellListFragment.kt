package com.example.todoapp.sell

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.*
import com.example.todoapp.buy.BuyListItem
import com.example.todoapp.databinding.SellListFragmentBinding
import com.example.todoapp.viewmodel.SellListViewModel
import com.example.todoapp.viewmodel.SellListViewModelFactory

class SellListFragment: BindableFragment<SellListFragmentBinding>(), ToolbarHelper {

    private val viewModel: SellListViewModel by viewModels {
        SellListViewModelFactory((requireActivity().application as TodoApplication).repository)
    }

    override fun getLayoutId() = R.layout.sell_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initToolbar(getString(R.string.sell_list))

        val adapter = SimpleRecyclerAdapter()
        viewModel.allProducts.observe(viewLifecycleOwner) {
            adapter.setItems(it.map { product ->
                BuyListItem(product, adapter)
            })
        }
        getFragmentBinding()?.rvList?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    companion object {
        private val TAG: String = SellListFragment::class.java.simpleName
        @JvmStatic fun newInstance() = SellListFragment()
    }

    override fun getAppCompatActivity(): AppCompatActivity = activity as AppCompatActivity

    override fun getToolbar(): Toolbar? = getFragmentBinding()?.header?.toolbar

    override fun getBinding(): ViewDataBinding? = getFragmentBinding()
}