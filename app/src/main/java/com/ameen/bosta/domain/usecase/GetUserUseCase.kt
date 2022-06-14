package com.ameen.bosta.domain.usecase

import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.domain.model.User
import com.ameen.bosta.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: IUserRepository) {
    fun execute(): Flow<ResultWrapper<User>> {
        return flow {
            emit(repo.getUser())
        }
    }
}