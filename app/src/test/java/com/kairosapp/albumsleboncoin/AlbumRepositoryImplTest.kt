package com.kairosapp.albumsleboncoin

import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.model.AlbumAPI
import com.kairosapp.albumsleboncoin.repository.AlbumRepositoryImpl
import com.kairosapp.albumsleboncoin.service.LeboncoinService
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AlbumRepositoryImplTest: BaseTest() {

    @Test
    fun testThatGetAlbumsReturnsTheCorrectList() = runBlockingTest {
        val (albumRepository) = createTestData()

        val albumListResult = albumRepository.getAlbums()

        val expectedResult = listOf(albumApi1.toModel(), albumApi2.toModel())
        assertEquals(expectedResult, albumListResult)
    }

    @Test
    fun testThatGetAlbumsReturnsTheCorrectListWhenAlbumListIsEmpty() = runBlockingTest {
        val (albumRepository, leboncoinService) = createTestData()

        Mockito.`when`(leboncoinService.getAlbums()).thenReturn(emptyList())
        val albumListResult = albumRepository.getAlbums()

        val expectedResult = emptyList<Album>()
        assertEquals(expectedResult, albumListResult)
    }

    fun createTestData(): Pair<AlbumRepositoryImpl, LeboncoinService> = runBlocking{
        val leboncoinServiceMock = Mockito.mock(LeboncoinService::class.java)

        val albumApiList = listOf(albumApi1,albumApi2)

        Mockito.`when`(leboncoinServiceMock.getAlbums()).thenReturn(albumApiList)

        return@runBlocking Pair(AlbumRepositoryImpl(leboncoinServiceMock), leboncoinServiceMock)
    }

    companion object {
        val albumApi1 = AlbumAPI(1,1, "Title 1", "url1.com", "thumbnailurl1.com")
        val albumApi2 = AlbumAPI(1,2, "Title 2", "url2.com", "thumbnailurl2.com")
    }
}