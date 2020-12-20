package com.kairosapp.albumsleboncoin.ui.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.repository.AlbumRepository
import com.kairosapp.albumsleboncoin.repository.AlbumRepositoryImpl
import com.kairosapp.albumsleboncoin.util.launchCoroutine

class AlbumListViewModelImpl @ViewModelInject constructor(
    private val albumRepository: AlbumRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(), AlbumListViewModel {

    val state = MutableLiveData<State>()

    init {
        state.value = State.NotStarted
        fetchAlbums()
    }

    private fun fetchAlbums() {
        state.value = State.Loading

        viewModelScope.launchCoroutine({
            val albumList = albumRepository.getAlbums()
            state.value = State.Loaded(albumList)
        }, { error ->
            state.value = State.Error(error)
            Log.d("AlbumListViewModelImpl", "fetchAlbums: ${error.message}")
        })
    }

    sealed class State() {
        object NotStarted: State()
        object Loading: State()
        class Loaded(val albumList: List<Album>): State()
        class Error(val error: Throwable): State()
    }
}