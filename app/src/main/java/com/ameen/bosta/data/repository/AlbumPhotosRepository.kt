package com.ameen.bosta.data.repository

import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.data.mapper.AlbumPhotoDataMapper
import com.ameen.bosta.data.remote.PhotoApi
import com.ameen.bosta.domain.model.Photo
import com.ameen.bosta.domain.repository.IAlbumPhotosRepository
import javax.inject.Inject

class AlbumPhotosRepository @Inject constructor(
    private val api: PhotoApi,
    private val albumPhotoDataMapper: AlbumPhotoDataMapper
) : IAlbumPhotosRepository {

    override suspend fun getAlbumPhotos(albumId: Int): ResultWrapper<List<Photo>> {
        return try {
            val response = api.getAlbumPhotos(albumId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return ResultWrapper.Success(
                        albumPhotoDataMapper.albumPhotosResponseToPhotoViewState(it)
                    )
                } ?: ResultWrapper.Error("Something Happen Please Try Again!!")
            } else {
                ResultWrapper.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ResultWrapper.Error(e.message.toString())
        }
    }

}