package com.sbor.youtubeapi.core.ui.ui.videoplayer

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.sbor.youtubeapi.R
import com.sbor.youtubeapi.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.sbor.youtubeapi.core.ui.ui.BaseActivity
import org.chromium.base.Callback

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {
    private val id: String?
        get() = intent.getStringExtra("id_video")
    private val title: String?
        get() = intent.getStringExtra("title_video")
    private val description: String?
        get() = intent.getStringExtra("description_video")

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition : Long = 0
    override val viewModel: PlayerViewModel
        get() = ViewModelProvider(this)[PlayerViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id?.let {
            viewModel.getVideo(id= it).observe(this){

                binding.title.text = title
                binding.description.text = description

            }
        }

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }
    override fun initListeners() {
        super.initListeners()
        binding.back.setOnClickListener {
            finish()
        }
        binding.download.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setView(R.layout.dialog_window)
            val dialog = builder.create()
            dialog.show()
        }
    }
    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        val videoUrl = "https://www.youtube.com/watch?v=GG_aOIG1JWg"
        object : YouTubeExtractor(this){
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null){
                    val itag = 137
                    val audiotag = 140
                    val videourl = ytFiles[itag].url
                    val audiourl = ytFiles[audiotag].url
                    val aydiosource: ProgressiveMediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource
                        .Factory()).createMediaSource(MediaItem.fromUri(audiourl))
                    val videoSource: ProgressiveMediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource
                        .Factory()).createMediaSource(MediaItem.fromUri(videourl))
                    player!!.setMediaSource(MergingMediaSource(true,
                        videoSource,aydiosource ),true)
                    player!!.prepare()
                    player!!.playWhenReady = playWhenReady
                    player!!.seekTo(currentWindow,playbackPosition)
                    binding.videoView.player = player
                }
            }

        }.extract(videoUrl,false,true)

    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        initView()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
            hideSystemUi()
        }
    }

    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
    private fun releasePlayer() {
        if (player != null){
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }
    }

}