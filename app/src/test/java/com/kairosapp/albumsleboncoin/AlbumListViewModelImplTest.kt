package com.kairosapp.albumsleboncoin

import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.repository.AlbumRepository
import com.kairosapp.albumsleboncoin.ui.viewmodel.AlbumListViewModelImpl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AlbumListViewModelImplTest : BaseTest() {

    private lateinit var albumRepositoryMock : AlbumRepository

    @Before
    fun setUp() {
        albumRepositoryMock = Mockito.mock(AlbumRepository::class.java)
    }

    @Test
    fun testThatFinalStateIsLoadedIfAlbumFetchSuccessful() = runBlockingTest {
        val albumList = listOf(album1, album2)
        Mockito.`when`(albumRepositoryMock.getAlbums()).thenReturn(albumList)

        val albumListViewModel = AlbumListViewModelImpl(albumRepositoryMock)

        Mockito.verify(albumRepositoryMock).getAlbums()

        val finalState = albumListViewModel.state.value
        val expectedFinalState = AlbumListViewModelImpl.State.Loaded(albumList)

        assertEquals(expectedFinalState, finalState)
    }

    @Test
    fun testThatFinalStateIsErrorIfAlbumFetchFailed() = runBlockingTest {
        val error = Throwable("Error")
        Mockito.`when`(albumRepositoryMock.getAlbums()).thenAnswer {
            throw error
        }

        val albumListViewModel = AlbumListViewModelImpl(albumRepositoryMock)

        Mockito.verify(albumRepositoryMock).getAlbums()

        val finalState = albumListViewModel.state.value
        val expectedFinalState = AlbumListViewModelImpl.State.Error(error)

        assertEquals(expectedFinalState, finalState)
    }

    companion object {
        val album1 = Album(1, "Title 1", "url1.com", "thumbnailurl1.com")
        val album2 = Album(2, "Title 2", "url2.com", "thumbnailurl2.com")
    }
}