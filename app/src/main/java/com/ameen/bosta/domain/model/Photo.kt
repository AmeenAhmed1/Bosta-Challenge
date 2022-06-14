package com.ameen.bosta.domain.model

import java.io.Serializable

data class Photo(
    val photoId: Int,
    val photoTitle: String,
    val photoUrl: String
) : Serializable
