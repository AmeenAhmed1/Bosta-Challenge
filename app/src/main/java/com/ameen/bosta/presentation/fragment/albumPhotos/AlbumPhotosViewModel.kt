package com.ameen.bosta.presentation.fragment.albumPhotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.domain.model.Photo
import com.ameen.bosta.domain.usecase.GetAlbumPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumPhotosViewModel @Inject constructor(
    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCase
) : ViewModel() {

    private val _albumPhotoData: MutableStateFlow<ResultWrapper<List<Photo>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val albumPhotoData = _albumPhotoData


    fun getAlbumPhotos(albumId: Int) {
        getAlbumPhotosUseCase.execute(albumId).flowOn(Dispatchers.IO)
            .onEach {
                when (it) {
                    is ResultWrapper.Success -> _albumPhotoData.emit(ResultWrapper.Success(it.value))
                    is ResultWrapper.Error -> _albumPhotoData.emit(ResultWrapper.Error(it.error))
                }
            }.launchIn(viewModelScope)
    }
}