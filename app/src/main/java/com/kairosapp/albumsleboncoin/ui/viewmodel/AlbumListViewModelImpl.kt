package com.kairosapp.albumsleboncoin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kairosapp.albumsleboncoin.model.Album
import com.kairosapp.albumsleboncoin.repository.AlbumRepositoryImpl
import com.kairosapp.albumsleboncoin.util.launchCoroutine

class AlbumListViewModelImpl : ViewModel() {
    val state = MutableLiveData<State>()

    private val albumRepository = AlbumRepositoryImpl()

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