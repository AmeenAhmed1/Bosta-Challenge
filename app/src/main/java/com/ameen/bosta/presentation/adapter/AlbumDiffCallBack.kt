package com.ameen.bosta.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.bosta.domain.model.Album

object AlbumDiffCallBack : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.albumId == newItem.albumId
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}