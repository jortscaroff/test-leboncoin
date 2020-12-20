package com.kairosapp.albumsleboncoin.ui.recycler

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kairosapp.albumsleboncoin.R
import com.kairosapp.albumsleboncoin.model.Album
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

private const val PICASSO_DISK_CACHE_SIZE_LIMIT = 25L * 1024L * 1024L // 25mo

class AlbumAdapter(context: Context, private val albumList: List<Album>): RecyclerView.Adapter<AlbumViewHolder>() {

    val picasso = Picasso.Builder(context)
        .downloader(
            OkHttp3Downloader(
                OkHttpClient.Builder()
                    .cache(Cache(File(context.cacheDir, "images"), PICASSO_DISK_CACHE_SIZE_LIMIT))
                    .build()
            )
        )
        .build()


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

        val placeholder = ColorDrawable(ContextCompat.getColor(holder.itemView.context, R.color.image_placeholder_background))

        picasso.load(album.thumbnailUrl).placeholder(placeholder).into(holder.itemView.album_thumbnail);

    }
}

class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}