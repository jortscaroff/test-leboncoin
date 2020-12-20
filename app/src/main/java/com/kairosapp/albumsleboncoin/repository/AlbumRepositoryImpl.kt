package com.kairosapp.albumsleboncoin.repository

import com.google.gson.GsonBuilder
import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepositoryImpl : AlbumRepository {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://static.leboncoin.fr")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    val service = retrofit.create(LeboncoinService::class.java)

    override suspend fun getAlbums(): List<Album> {
        val albumsList: MutableList<Album> = mutableListOf()

        val apiAlbums = service.getAlbums()

        apiAlbums.forEach { album ->
            albumsList.add(album.toModel())
        }

        return albumsList
    }


}