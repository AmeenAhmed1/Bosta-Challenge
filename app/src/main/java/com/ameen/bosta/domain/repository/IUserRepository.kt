package com.ameen.bosta.domain.repository

interface IUserRepository {
    fun getUser()
    fun getUserAlbums(userId: String)
    fun getAlbumPhotos(albumId: String)
}