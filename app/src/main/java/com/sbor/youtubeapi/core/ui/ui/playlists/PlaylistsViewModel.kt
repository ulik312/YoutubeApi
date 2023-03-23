package com.sbor.youtubeapi.core.ui.ui.playlists

import androidx.lifecycle.LiveData
import com.sbor.youtubeapi.App.Companion.repozitory
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.core.ui.ui.BaseViewModel
import com.sbor.youtubeapi.data.remote.model.Playlists

open class PlaylistsViewModel: BaseViewModel() {

    fun getplaylist(): LiveData<Resource<Playlists>> {
        return repozitory.getPlayLists()
    }

}