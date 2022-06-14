package com.ameen.bosta.presentation.listener

import com.ameen.bosta.domain.model.Photo

interface PhotoClickListener {
    fun onPhotoSelectedListener(photoData: Photo)
}