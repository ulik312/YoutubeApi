package com.sbor.youtubeapi.repozitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbor.youtubeapi.BuildConfig
import com.sbor.youtubeapi.core.ui.network.RetrofitClient
import com.sbor.youtubeapi.core.ui.network.result.Resource
import com.sbor.youtubeapi.data.remote.ApiService
import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repozitory {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlayLists(): LiveData<Resource<Playlists>> {

        val data = MutableLiveData<Resource<Playlists>>()
        data.value = Resource.loading()

        apiService.getPlaylists(
            com.sbor.youtubeapi.BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails",
            50
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    data.value = Resource.error(t.message, null, null)

                }

            })
        return data
    }

    fun getItemList(id: String): LiveData<PlaylistItem>{
        val data2 = MutableLiveData<PlaylistItem>()
        apiService.getItemLists(
            BuildConfig.API_KEY,
            "snippet,contentDetails",
            50,
            id
        ).enqueue(object : Callback<PlaylistItem> {
            override fun onResponse(
                call: Call<PlaylistItem>,
                response: Response<PlaylistItem>
            ) {
                if (response.isSuccessful) {
                    data2.value = response.body()
                }
            }

            override fun onFailure(call: Call<PlaylistItem>, t: Throwable) {
            }
        })
        return data2
    }
}