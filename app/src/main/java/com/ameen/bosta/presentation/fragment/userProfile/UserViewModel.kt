package com.ameen.bosta.presentation.fragment.userProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.model.User
import com.ameen.bosta.domain.usecase.GetUserAlbumsUseCase
import com.ameen.bosta.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase
) : ViewModel() {

    private val _userData: MutableStateFlow<ResultWrapper<User>> =
        MutableStateFlow(ResultWrapper.Loading)
    val userData = _userData

    private val _userAlbums: MutableStateFlow<ResultWrapper<List<Album>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val userAlbum = _userAlbums

    init {
        getUser()
    }

    fun getUser() {
        getUserUseCase.execute().flowOn(Dispatchers.IO).onEach {
            when (it) {
                is ResultWrapper.Success -> {
                    _userData.emit(ResultWrapper.Success(it.value))
                    getUserAlbums(it.value.userId)
                }
                is ResultWrapper.Error -> _userData.emit(ResultWrapper.Error(it.error))
            }
        }.launchIn(viewModelScope)
    }


    private fun getUserAlbums(userId: Int) {
        getUserAlbumsUseCase.execute(userId).flowOn(Dispatchers.IO).onEach {
            when (it) {
                is ResultWrapper.Success -> _userAlbums.emit(ResultWrapper.Success(it.value))
                is ResultWrapper.Error -> _userData.emit(ResultWrapper.Error(it.error))
            }
        }.launchIn(viewModelScope)
    }

}