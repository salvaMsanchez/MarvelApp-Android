package com.example.marvelapp.di

import com.example.marvelapp.data.remote.RemoteDataSource
import com.example.marvelapp.data.remote.RemoteDataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindsRemoteDataSourceInterface(remoteDataSource: RemoteDataSource): RemoteDataSourceInterface
}