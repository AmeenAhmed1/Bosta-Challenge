package com.ameen.bosta.domain.usecase

import com.ameen.bosta.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: IUserRepository) {
    fun execute() {
        return repo.getUser()
    }
}