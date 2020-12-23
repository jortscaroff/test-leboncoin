package com.kairosapp.albumsleboncoin

import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.model.AlbumAPI
import org.junit.Assert
import org.junit.Test

class AlbumAPITest {

    @Test
    fun testThatToModelReturnsTheCorrectAlbumFromAlbumApi() {
        val albumApi = AlbumAPI(1,1, "Title 1", "url1.com", "thumbnailurl1.com")

        val albumResult = albumApi.toModel()
        val expectedResult = Album(albumApi.id, albumApi.title, albumApi.url, albumApi.thumbnailUrl)

        Assert.assertEquals(expectedResult, albumResult)
    }

}