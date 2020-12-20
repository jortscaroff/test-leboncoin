package com.kairosapp.albumsleboncoin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kairosapp.albumsleboncoin.R
import com.kairosapp.albumsleboncoin.ui.recycler.AlbumAdapter
import com.kairosapp.albumsleboncoin.ui.viewmodel.AlbumListViewModelImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private val albumListViewModel: AlbumListViewModelImpl by lazy {
        ViewModelProvider(this).get(AlbumListViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        album_recycler_view.layoutManager = LinearLayoutManager(this)

        albumListViewModel.state.observe(this, Observer { state ->
            Log.d("MainActivity", "onCreate: $state")
            when (state) {
                AlbumListViewModelImpl.State.Loading -> {
                    album_recycler_view.visibility = View.GONE
                    album_progress_bar.visibility = View.VISIBLE
                }
                is AlbumListViewModelImpl.State.Loaded -> {
                    album_progress_bar.visibility = View.GONE
                    album_recycler_view.visibility = View.VISIBLE

                    album_recycler_view.adapter = AlbumAdapter(this, state.albumList)
                }
                is AlbumListViewModelImpl.State.Error -> {
                    album_progress_bar.visibility = View.GONE
                }
            }
        })
    }

}