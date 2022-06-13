package com.ameen.bosta.domain.repository

import com.ameen.bosta.core.ResultWrapper
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.model.User

interface IUserRepository {
    suspend fun getUser(): ResultWrapper<User>
    suspend fun getUserAlbums(userId: Int): ResultWrapper<List<Album>>
    fun getAlbumPhotos(albumId: String)
}