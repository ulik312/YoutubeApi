package com.sbor.youtubeapi.data.remote

import com.sbor.youtubeapi.data.remote.model.PlaylistItem
import com.sbor.youtubeapi.data.remote.model.Playlists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): Response<Playlists>

    @GET("playlistItems")
    suspend fun getItemLists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("playlistId") id: String,
    ): Response<PlaylistItem>

    @GET("videos")
    suspend fun getVideo(
        @Query("key")apiKey: String,
        @Query("part")part: String,
        @Query("id") id: String
    ):Response<Playlists>
}