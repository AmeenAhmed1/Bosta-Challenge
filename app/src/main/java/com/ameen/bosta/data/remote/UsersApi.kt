package com.ameen.bosta.data.remote

import com.ameen.bosta.core.util.ApiEndPoints
import com.ameen.bosta.data.model.AlbumResponse
import com.ameen.bosta.data.model.PhotoResponse
import com.ameen.bosta.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET(ApiEndPoints.USERS_END_POINT)
    suspend fun getUserData(): Response<List<UserResponse>>

    @GET(ApiEndPoints.USERS_ALBUMS_END_POINT)
    suspend fun getUserAlbums(
        @Query("userId") userId: Int
    ): Response<List<AlbumResponse>>

}