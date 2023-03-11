package com.sbor.youtubeapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbor.youtubeapi.BuildConfig
import com.sbor.youtubeapi.base.BaseViewModel
import com.sbor.youtubeapi.model.PlaylistItem
import com.sbor.youtubeapi.remote.ApiService
import com.sbor.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class DetailViewModel : BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }
    fun itemList(id: String): LiveData<PlaylistItem> {
        return getItemList(id = id)

    }

    private fun getItemList(id: String): LiveData<PlaylistItem> {
        val data2 = MutableLiveData<PlaylistItem>()
        apiService.getItemLists(
            BuildConfig.API_KEY,
            "snippet",
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
                print(t.message)
            }
        })
        return data2
    }
}