package com.kairosapp.albumsleboncoin.model

/**
 * Data class to hold data for an Album Item
 *
 * @param id album id
 * @param title album title
 * @param url album url
 * @param thumbnailUrl album thumbnail url
 */

data class Album (
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)