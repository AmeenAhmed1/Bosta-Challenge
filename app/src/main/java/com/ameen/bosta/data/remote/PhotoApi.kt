package com.ameen.bosta.data.remote

import com.ameen.bosta.core.util.ApiEndPoints
import com.ameen.bosta.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET(ApiEndPoints.ALBUM_PHOTOS_END_POINT)
    suspend fun getAlbumPhotos(
        @Query("albumId") albumId: Int
    ): Response<List<PhotoResponse>>

}