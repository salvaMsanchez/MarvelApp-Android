package com.example.marvelapp.data

import com.example.marvelapp.data.mappers.LocalToUiMapper
import com.example.marvelapp.data.mappers.RemoteToLocalMapper
import com.example.marvelapp.data.mappers.RemoteToUiMapper
import com.example.marvelapp.fakes.FakeLocalDataSource
import com.example.marvelapp.fakes.FakeRemoteDataSource
import org.junit.Before

class RepositoryTest {

    // SUT
    private lateinit var repository: Repository

    // Dependencies
    private val localDataSource: FakeLocalDataSource = FakeLocalDataSource()
    private val remoteDataSource: FakeRemoteDataSource = FakeRemoteDataSource()
    private val localToUiMapper: LocalToUiMapper = LocalToUiMapper()
    private val remoteToLocalMapper: RemoteToLocalMapper = RemoteToLocalMapper()
    private val remoteToUiMapper: RemoteToUiMapper = RemoteToUiMapper()

    @Before
    fun setUp() {
        repository = Repository(remoteDataSource, localDataSource, remoteToLocalMapper, localToUiMapper, remoteToUiMapper)
    }
}