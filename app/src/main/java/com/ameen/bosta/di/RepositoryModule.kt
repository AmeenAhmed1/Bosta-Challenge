package com.ameen.bosta.di

import com.ameen.bosta.data.mapper.UserDataMapper
import com.ameen.bosta.data.remote.UsersApi
import com.ameen.bosta.data.repository.UserRepository
import com.ameen.bosta.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(api: UsersApi, dataMapper: UserDataMapper) =
        UserRepository(api, dataMapper) as IUserRepository


    @Provides
    @Singleton
    fun provideDataMapper(): UserDataMapper = UserDataMapper()
}