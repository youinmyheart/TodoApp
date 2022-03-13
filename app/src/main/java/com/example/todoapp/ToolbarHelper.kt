package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding

interface ToolbarHelper {
    fun getAppCompatActivity(): AppCompatActivity?
    fun getToolbar(): Toolbar?
    fun getBinding(): ViewDataBinding? = null

    fun initToolbar(title : String) {
        getToolbar()?.let { toolbar ->
            getBinding()?.setVariable(BR.title, title)
            getAppCompatActivity()?.let { activity ->
                activity.setSupportActionBar(toolbar)
                activity.supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(true)
                    setHomeAsUpIndicator(R.drawable.back)
                    setDisplayShowTitleEnabled(false)
                }

                toolbar.setNavigationOnClickListener {
                    activity.onBackPressed()
                }
            }
        }
    }
}