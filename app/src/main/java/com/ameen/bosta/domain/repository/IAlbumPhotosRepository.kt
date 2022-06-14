package com.ameen.bosta.domain.repository

import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.domain.model.Photo

interface IAlbumPhotosRepository {
    suspend fun getAlbumPhotos(albumId: Int): ResultWrapper<List<Photo>>
}