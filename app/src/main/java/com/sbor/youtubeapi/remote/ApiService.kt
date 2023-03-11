package com.sbor.youtubeapi.remote

import com.sbor.youtubeapi.model.PlaylistItem
import com.sbor.youtubeapi.model.Playlists
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
    ):Call<PlaylistItem>


}