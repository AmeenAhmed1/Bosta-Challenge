package com.ameen.bosta.di

import com.ameen.bosta.data.mapper.AlbumPhotoDataMapper
import com.ameen.bosta.data.mapper.UserDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideDataMapper(): UserDataMapper = UserDataMapper()

    @Provides
    @Singleton
    fun provideAlbumPhotoMapper(): AlbumPhotoDataMapper = AlbumPhotoDataMapper()
}