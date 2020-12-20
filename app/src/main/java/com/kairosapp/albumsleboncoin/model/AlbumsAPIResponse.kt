package com.kairosapp.albumsleboncoin.model

data class AlbumsAPIResponse(val albumsAPIList: List<AlbumAPI>) {

    fun toModel(): List<Album> {
        val albumsList: MutableList<Album> = mutableListOf()

        albumsAPIList.forEach { album ->
            albumsList.add(album.toModel())
        }

        return albumsList
    }
}
