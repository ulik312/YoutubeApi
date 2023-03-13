package com.sbor.youtubeapi.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbor.youtubeapi.R
import com.sbor.youtubeapi.adapter.PlaylistAdapter
import com.sbor.youtubeapi.base.BaseActivity
import com.sbor.youtubeapi.databinding.PlaylistsMainBinding
import com.sbor.youtubeapi.internet.Connection
import com.sbor.youtubeapi.detail.PlaylistDetailActivity
import com.sbor.youtubeapi.model.Item

class PlaylistsActivity: BaseActivity<PlaylistsMainBinding, PlaylistsViewModel>() {
    private lateinit var adapter:PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PlaylistAdapter(onItemClick = this::onItemClick)

    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)

    }
    override fun initView() {
        viewModel.playlist().observe(this) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
            adapter.setItem(it.items as ArrayList<Item>)
//            Toast.makeText(this, it.etag.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.connectionView.tryAgain.setOnClickListener {
            checkConnection()
        }

    }
    private fun onItemClick(list: Item){
        Intent(this@PlaylistsActivity, PlaylistDetailActivity::class.java).apply {
            putExtra("id", list.id)
            startActivity(this)
        }

    }

    override fun isConnection() {
        checkConnection()

    }
    private fun checkConnection() {
        val isConnection = Connection.isNetworkAvailable(this)
        if (!isConnection){
            Toast.makeText(this, getString(R.string.not_connection_message), Toast.LENGTH_SHORT).show()
        }
        binding.connectionContainer.isVisible = !isConnection

        //com.sbor.youtubeapi.BuildConfig
    }

}

