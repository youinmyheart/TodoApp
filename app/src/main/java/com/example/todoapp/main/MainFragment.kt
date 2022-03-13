package com.example.todoapp.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.todoapp.BindableFragment
import com.example.todoapp.R
import com.example.todoapp.databinding.MainFragmentBinding
import com.example.todoapp.viewmodel.CustomerListViewModel

class MainFragment: BindableFragment<MainFragmentBinding>() {

    private lateinit var viewModel: CustomerListViewModel

    override fun getLayoutId() = R.layout.main_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
        viewModel = ViewModelProviders.of(requireActivity())[CustomerListViewModel::class.java]

        getFragmentBinding()?.btnCallList?.setOnClickListener {
            viewModel.getMainActionListener().onTapCallList()
        }
        getFragmentBinding()?.btnBuyList?.setOnClickListener {
            viewModel.getMainActionListener().onTapBuyList()
        }
        getFragmentBinding()?.btnSellList?.setOnClickListener {
            viewModel.getMainActionListener().onTapSellList()
        }
    }

    companion object {
        private val TAG: String = MainFragment::class.java.simpleName
        @JvmStatic fun newInstance() = MainFragment()
    }
}