package com.ameen.bosta.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.bosta.domain.model.Photo

object AlbumPhotosDiffCallBack : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.photoId == newItem.photoId
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}