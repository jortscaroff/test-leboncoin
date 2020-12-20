package com.kairosapp.albumsleboncoin.model

import com.google.gson.annotations.SerializedName


data class AlbumAPI(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
    ) {
    fun toModel(): Album {
        return Album(id, title, url, thumbnailUrl)
    }
}