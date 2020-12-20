package com.kairosapp.albumsleboncoin.repository

import com.kairosapp.albumsleboncoin.model.Album

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
}