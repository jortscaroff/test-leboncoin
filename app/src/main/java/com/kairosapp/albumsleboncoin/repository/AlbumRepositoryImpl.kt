package com.kairosapp.albumsleboncoin.repository

import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val leboncoinService: LeboncoinService) : AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        val albumsList: MutableList<Album> = mutableListOf()

        val apiAlbums = leboncoinService.getAlbums()

        apiAlbums.forEach { album ->
            albumsList.add(album.toModel())
        }

        return albumsList
    }


}