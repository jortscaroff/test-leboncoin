package com.kairosapp.albumsleboncoin.repository

import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val leboncoinService: LeboncoinService) : AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        val albumList: MutableList<Album> = mutableListOf()

        val apiAlbums = leboncoinService.getAlbums()

        apiAlbums.forEach { album ->
            albumList.add(album.toModel())
        }

        return albumList
    }


}