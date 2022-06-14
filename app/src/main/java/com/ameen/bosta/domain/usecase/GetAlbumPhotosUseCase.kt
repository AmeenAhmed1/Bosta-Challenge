package com.ameen.bosta.domain.usecase

import com.ameen.bosta.data.repository.AlbumPhotosRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(private val repo: AlbumPhotosRepository) {
    fun execute(albumId: Int) = flow {
        emit(repo.getAlbumPhotos(albumId))
    }
}