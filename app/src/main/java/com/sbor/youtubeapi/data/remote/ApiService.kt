package com.sbor.youtubeapi.data.remote

import android.accounts.AuthenticatorDescription
import com.sbor.youtubeapi.data.remote.model.Localized
import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): retrofit2.Call<Playlists>

    @GET("playlistItems")
    fun getItemLists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("playlistId") id: String,
    ): Call<PlaylistItem>
}