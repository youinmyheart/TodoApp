package com.example.todoapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.lifecycle.ViewModelProviders
import com.example.todoapp.R
import com.example.todoapp.buy.BuyListFragment
import com.example.todoapp.call.CallListFragment
import com.example.todoapp.sell.SellListFragment
import com.example.todoapp.viewmodel.CustomerListViewModel


class MainActivity : AppCompatActivity(), CustomerListViewModel.MainActionListener {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
    private lateinit var viewModel: CustomerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        viewModel = ViewModelProviders.of(this)[CustomerListViewModel::class.java]
        viewModel.setMainActionListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment.newInstance(), MainFragment::class.java.name)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onTapCallList() {
        Log.d(TAG, "onTapCallList")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CallListFragment.newInstance(), CallListFragment::class.java.name)
            .addToBackStack(CallListFragment::class.java.name)
            .commit()
    }

    override fun onTapBuyList() {
        Log.d(TAG, "onTapBuyList")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BuyListFragment.newInstance(), BuyListFragment::class.java.name)
            .addToBackStack(BuyListFragment::class.java.name)
            .commit()
    }

    override fun onTapSellList() {
        Log.d(TAG, "onTapSellList")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SellListFragment.newInstance(), SellListFragment::class.java.name)
            .addToBackStack(SellListFragment::class.java.name)
            .commit()
    }
}