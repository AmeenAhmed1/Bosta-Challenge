package com.ameen.bosta.data.mapper

import com.ameen.bosta.data.model.PhotoResponse
import com.ameen.bosta.domain.model.Photo

class AlbumPhotoDataMapper {
    fun albumPhotosResponseToPhotoViewState(albumPhotoResponse: List<PhotoResponse>) =
        albumPhotoResponse.map {
            Photo(
                photoId = it.id,
                photoTitle = it.title,
                photoUrl = it.url
            )
        }
}