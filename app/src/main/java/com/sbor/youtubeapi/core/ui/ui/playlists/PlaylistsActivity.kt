package com.sbor.youtubeapi.core.ui.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.sbor.youtubeapi.R
import com.sbor.youtubeapi.core.ui.network.result.Status
import com.sbor.youtubeapi.core.ui.ui.BaseActivity
import com.sbor.youtubeapi.core.ui.ui.adapter.PlaylistAdapter
import com.sbor.youtubeapi.core.ui.ui.detail.PlaylistDetailActivity
import com.sbor.youtubeapi.databinding.PlaylistsMainBinding
import com.sbor.youtubeapi.internet.Connection
import com.sbor.youtubeapi.data.remote.model.Item


class PlaylistsActivity: BaseActivity<PlaylistsMainBinding, PlaylistsViewModel>() {
    private lateinit var adapter: PlaylistAdapter

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
        viewModel.loading.observe(this){
            binding.progressBar.isVisible = it

        }
        viewModel.getplaylist().observe(this) {

            when(it.status){
                Status.SUCCESS ->{
                    binding.recyclerView.adapter = adapter
                    adapter.setItem(it.data?.items as ArrayList<Item>)
                    viewModel.loading.postValue(false)
//            Toast.makeText(this, it.etag.toString(), Toast.LENGTH_SHORT).show()
                }
                Status.LOADING ->{
                    viewModel.loading.postValue(true)
                }
                Status.ERROR ->{
                    viewModel.loading.postValue(true)
                }
            }


        }
        binding.connectionView.tryAgain.setOnClickListener {
            checkConnection()
        }

    }

    private fun onItemClick(list: Item) {
        Intent(this@PlaylistsActivity, PlaylistDetailActivity::class.java).apply {
            putExtra("id", list.id)
            putExtra("title", list.snippet.title)
            putExtra("description", list.snippet.description)
            startActivity(this)
        }

    }

    override fun isConnection() {
        checkConnection()

    }

    private fun checkConnection() {

        val isConnection = Connection.isNetworkAvailable(this)
        if (!isConnection) {
            Toast.makeText(this, getString(R.string.not_connection_message), Toast.LENGTH_SHORT)
                .show()
        }
        binding.connectionContainer.isVisible = !isConnection
        //com.sbor.youtubeapi.BuildConfig = ulikresultget
    }

}