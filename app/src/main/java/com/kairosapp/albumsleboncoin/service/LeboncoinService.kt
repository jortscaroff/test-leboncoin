package com.kairosapp.albumsleboncoin.service

import com.kairosapp.albumsleboncoin.model.AlbumAPI
import retrofit2.http.GET

interface LeboncoinService {
    @GET("/img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumAPI>
}