package com.sbor.youtubeapi.data.remote

import com.sbor.youtubeapi.BuildConfig
import com.sbor.youtubeapi.core.ui.network.BaseDataSource
import com.sbor.youtubeapi.core.ui.network.RetrofitClient
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.data.remote.model.Playlists

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlayLists(): Resource<Playlists> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                "snippet,contentDetails",
                50)

        }
    }
    suspend fun getItemList(id: String) : Resource<PlaylistItem>{
        return getResult {
            apiService.getItemLists(
                BuildConfig.API_KEY,
                "snippet,contentDetails",
                50,
                id
            )
        }
    }
    suspend fun getVideoPlayer(id: String): Resource<Playlists>{
        return getResult {
            apiService.getVideo(
                BuildConfig.API_KEY,
                "snippet,contentDetails",
                id

            )
        }
    }
}