package com.sbor.youtubeapi.repozitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.data.remote.RemoteDataSource
import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repozitory {
    private val dataSource : RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlayLists(): LiveData<Resource<Playlists>> {

        return liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val response = dataSource.getPlayLists()
            emit(response)
        }
    }

    fun getItemList(id: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val response = dataSource.getItemList(id)
            emit(response)
        }
    }

    fun getVideoPlayer(id: String): LiveData<Resource<Playlists>> {
        return liveData (Dispatchers.IO){
            emit(Resource.loading())

            val response = dataSource.getVideoPlayer(id)
            emit(response)

        }


    }
}