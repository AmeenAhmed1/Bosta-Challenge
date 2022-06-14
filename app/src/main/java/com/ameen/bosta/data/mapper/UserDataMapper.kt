package com.ameen.bosta.data.mapper

import com.ameen.bosta.data.model.AlbumResponse
import com.ameen.bosta.data.model.UserResponse
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.model.User

class UserDataMapper {

    fun userResponseToViewState(userResponse: UserResponse) =
        User(
            userId = userResponse.id,
            userName = userResponse.name,
            userAddress = "${userResponse.address.city}, ${userResponse.address.suite}, ${userResponse.address.street}, ${userResponse.address.zipcode}"
        )


    fun albumResponseToViewState(albumResponse: List<AlbumResponse>) =
        albumResponse.map {
            Album(
                albumId = it.id,
                albumTitle = it.title
            )
        }
}