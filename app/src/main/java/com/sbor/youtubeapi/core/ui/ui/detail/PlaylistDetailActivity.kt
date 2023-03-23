package com.sbor.youtubeapi.core.ui.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.sbor.youtubeapi.core.ui.ui.BaseActivity
import com.sbor.youtubeapi.core.ui.ui.adapter.VideoAdapter
import com.sbor.youtubeapi.core.ui.ui.videoplayer.PlayerActivity
import com.sbor.youtubeapi.data.remote.model.Item
import com.sbor.youtubeapi.databinding.ActivityPlaylistDetailBinding

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding, DetailViewModel>() {
    private lateinit var adapter: VideoAdapter
    private val id: String?
        get() = intent.getStringExtra("id")
    private val title: String?
        get() = intent.getStringExtra("title")
    private val description: String?
        get() = intent.getStringExtra("description")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = VideoAdapter(onVideoClick = this::onVideoClick)
        binding.title.text = title
        binding.description.text = description
        setselectedItems()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)

    }

    override fun initListeners() {
        super.initListeners()
        binding.back.setOnClickListener {
            finish()
        }
    }

    override fun initView() {
        super.initView()
        viewModel.loading.observe(this) {
            binding.loader.isVisible = it
        }

    }
    private fun onVideoClick(item : Item){
        val intent = Intent(this@PlaylistDetailActivity, PlayerActivity::class.java)
        intent.putExtra("id_video", item.id)
        intent.putExtra("title_video", item.snippet.title)
        intent.putExtra("description_video",item.snippet.description)
        startActivity(intent)
    }

    override val viewModel: DetailViewModel
        get() = ViewModelProvider(this)[DetailViewModel::class.java]

    private fun setselectedItems() {

        id.let { id ->
            if (id != null) {
                viewModel.itemList(id = id).observe(this) {
                    binding.rvPlaylist.adapter = adapter
                    it.data?.let { it1 -> adapter.setselectedList(it1.items) }
                }
            }
        }

    }
}