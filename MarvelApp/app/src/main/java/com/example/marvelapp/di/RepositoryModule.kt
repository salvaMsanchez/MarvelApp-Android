package com.example.marvelapp.di

import com.example.marvelapp.data.Repository
import com.example.marvelapp.domain.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepositoryInterface(repository: Repository): RepositoryInterface
}