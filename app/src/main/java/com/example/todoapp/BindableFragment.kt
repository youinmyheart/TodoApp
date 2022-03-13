package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class BindableFragment<T: ViewDataBinding>: Fragment() {

    private var binding: WeakReference<T>? = null
    internal fun getFragmentBinding(): T? = binding?.get()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WeakReference(DataBindingUtil.inflate(inflater, getLayoutId(), container, false))
        getFragmentBinding()?.lifecycleOwner = this@BindableFragment
        return getFragmentBinding()?.root
    }

    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }

    abstract fun getLayoutId(): Int
}