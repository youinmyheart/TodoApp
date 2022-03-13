package com.example.todoapp.call

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
import com.example.todoapp.databinding.CallListFragmentBinding
import com.example.todoapp.viewmodel.CustomerListViewModel


class CallListFragment: BindableFragment<CallListFragmentBinding>(), ToolbarHelper {

    private lateinit var viewModel: CustomerListViewModel

    override fun getLayoutId() = R.layout.call_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initToolbar(getString(R.string.call_list))

        val adapter = SimpleRecyclerAdapter()
        viewModel = ViewModelProviders.of(this)[CustomerListViewModel::class.java]
        viewModel.getCallList()
        viewModel.getCustomersLive().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setItems(it.map { customer ->
                    CallListItem(customer, adapter)
                })
            }
        }
        getFragmentBinding()?.rvList?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    companion object {
        private val TAG: String = CallListFragment::class.java.simpleName
        @JvmStatic fun newInstance() = CallListFragment()
    }

    override fun getAppCompatActivity(): AppCompatActivity = activity as AppCompatActivity

    override fun getToolbar(): Toolbar? = getFragmentBinding()?.header?.toolbar

    override fun getBinding(): ViewDataBinding? = getFragmentBinding()
}