package com.sbor.youtubeapi.base

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sbor.youtubeapi.R
import com.sbor.youtubeapi.adapter.PlaylistAdapter
import com.sbor.youtubeapi.databinding.UncludedLayoutBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    abstract fun inflateViewBinding(inflater: LayoutInflater): VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)



        isConnection()
        initViewModel()
        initView()
        initListeners()



    }

    open fun initViewModel() {}

    open fun isConnection() {}

    open fun initView() {}

    open fun initListeners() {}

}