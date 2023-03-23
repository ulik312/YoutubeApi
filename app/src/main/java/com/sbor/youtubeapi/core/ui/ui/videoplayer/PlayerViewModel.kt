package com.sbor.youtubeapi.core.ui.ui.videoplayer

import androidx.lifecycle.LiveData
import com.sbor.youtubeapi.App
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.core.ui.ui.BaseViewModel
import com.sbor.youtubeapi.data.remote.model.Playlists

class PlayerViewModel : BaseViewModel() {
    fun getVideo(id: String): LiveData<Resource<Playlists>> {
        return App.repozitory.getVideoPlayer(id)
    }
}