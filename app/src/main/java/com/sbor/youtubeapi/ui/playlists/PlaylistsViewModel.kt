package com.sbor.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbor.youtubeapi.BuildConfig
import com.sbor.youtubeapi.model.Playlists
import com.sbor.youtubeapi.base.BaseViewModel
import com.sbor.youtubeapi.remote.ApiService
import com.sbor.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class PlaylistsViewModel: BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlist(): LiveData<Playlists> {
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getPlaylists(
            com.sbor.youtubeapi.BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails",
            50
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {

                    print(t.message)
                }

            })
        return data
    }


}