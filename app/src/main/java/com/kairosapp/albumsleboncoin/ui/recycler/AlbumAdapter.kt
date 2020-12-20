package com.kairosapp.albumsleboncoin.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kairosapp.albumsleboncoin.R
import com.kairosapp.albumsleboncoin.model.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

class AlbumAdapter(private val albumList: List<Album>): RecyclerView.Adapter<AlbumViewHolder>() {
    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.album_row, parent, false)

        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.itemView.album_title.text = album.title
        Picasso.get().load(album.thumbnailUrl).into(holder.itemView.album_thumbnail);
    }
}

class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}