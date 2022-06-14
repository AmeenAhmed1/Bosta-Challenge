package com.ameen.bosta.data.repository

import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.data.mapper.UserDataMapper
import com.ameen.bosta.data.remote.UsersApi
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.model.User
import com.ameen.bosta.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UsersApi,
    private val dataMapper: UserDataMapper
) : IUserRepository {

    override suspend fun getUser(): ResultWrapper<User> {
        return try {
            val response = api.getUserData()
            if (response.isSuccessful) {
                response.body()?.let {
                    val randomUser = (0..it.lastIndex).shuffled().last()
                    return ResultWrapper.Success(dataMapper.userResponseToViewState(it[randomUser]))
                } ?: ResultWrapper.Error("Something Happen Please Try Again!!")
            } else {
                ResultWrapper.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ResultWrapper.Error(e.message.toString())
        }
    }


    override suspend fun getUserAlbums(userId: Int): ResultWrapper<List<Album>> {
        return try {
            val response = api.getUserAlbums(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return ResultWrapper.Success(dataMapper.albumResponseToViewState(it))
                } ?: ResultWrapper.Error("Something Happen Please Try Again!!")
            } else {
                ResultWrapper.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ResultWrapper.Error(e.message.toString())
        }
    }

    override fun getAlbumPhotos(albumId: String) {
        TODO("Not yet implemented")
    }
}