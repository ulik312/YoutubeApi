package com.sbor.youtubeapi.core.ui.ui.detail

import androidx.lifecycle.LiveData
import com.sbor.youtubeapi.App
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.core.ui.ui.BaseViewModel

open class DetailViewModel : BaseViewModel() {

    fun itemList(id: String): LiveData<Resource<PlaylistItem>> {
        return App.repozitory.getItemList(id = id)
    }
}