package com.example.todoapp.sell

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.example.todoapp.BindableFragment
import com.example.todoapp.R
import com.example.todoapp.ToolbarHelper
import com.example.todoapp.databinding.SellListFragmentBinding

class SellListFragment: BindableFragment<SellListFragmentBinding>(), ToolbarHelper {
    override fun getLayoutId() = R.layout.sell_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initToolbar(getString(R.string.sell_list))
    }

    companion object {
        private val TAG: String = SellListFragment::class.java.simpleName
        @JvmStatic fun newInstance() = SellListFragment()
    }

    override fun getAppCompatActivity(): AppCompatActivity = activity as AppCompatActivity

    override fun getToolbar(): Toolbar? = getFragmentBinding()?.header?.toolbar

    override fun getBinding(): ViewDataBinding? = getFragmentBinding()
}