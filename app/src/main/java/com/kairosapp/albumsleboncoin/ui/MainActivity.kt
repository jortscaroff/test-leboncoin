package com.kairosapp.albumsleboncoin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kairosapp.albumsleboncoin.R
import com.kairosapp.albumsleboncoin.ui.recycler.AlbumAdapter
import com.kairosapp.albumsleboncoin.ui.viewmodel.AlbumListViewModelImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val albumListViewModel: AlbumListViewModelImpl by lazy {
        ViewModelProvider(this).get(AlbumListViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        album_recyclerView.layoutManager = LinearLayoutManager(this)

        albumListViewModel.state.observe(this, Observer { state ->
            Log.d("MainActivity", "onCreate: $state")
            when (state) {
                AlbumListViewModelImpl.State.Loading -> {
                }
                is AlbumListViewModelImpl.State.Loaded -> {
                    Log.d("MainActivity", "onCreate: Loaded")
                    album_recyclerView.adapter = AlbumAdapter(state.albumList)
                }
                is AlbumListViewModelImpl.State.Error -> {
                }
            }
        })
    }

}