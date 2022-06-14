package com.ameen.bosta.domain.usecase

import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(private val repo: IUserRepository) {
    fun execute(userId: Int): Flow<ResultWrapper<List<Album>>> {
        return flow {
            emit(repo.getUserAlbums(userId))
        }
    }
}