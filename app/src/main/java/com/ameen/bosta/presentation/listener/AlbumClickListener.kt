package com.ameen.bosta.presentation.listener

import com.ameen.bosta.domain.model.Album

interface AlbumClickListener {
    fun onAlbumClicked(albumData: Album)
}