package com.example.marvelapp.data.remote

import com.example.marvelapp.base.BaseNetworkTest
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceTest: BaseNetworkTest() {

    // SUT
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun `WHEN getCharacters THEN success and return characters list`() = runTest {
        remoteDataSource = RemoteDataSource(api)
        val charactersLimit: String = "3"
        val charactersOffset: String = "0"

        val actual = remoteDataSource.getCharacters(charactersLimit, charactersOffset)

        Truth.assertThat(actual).hasSize(3)
        Truth.assertThat(actual[0].name).isEqualTo("3-D Man")
    }

    @Test
    fun `WHEN getComics THEN success and return comics list`() = runTest {
        remoteDataSource = RemoteDataSource(api)
        val charactersId: Long = 1009351

        val actual = remoteDataSource.getComics(charactersId)

        Truth.assertThat(actual).hasSize(20)
        Truth.assertThat(actual[0].title).isEqualTo("Incredible Hulks (2010) #604 (DJURDJEVIC 70TH ANNIVERSARY VARIANT)")
    }

    @Test
    fun `WHEN getSeries THEN success and return series list`() = runTest {
        remoteDataSource = RemoteDataSource(api)
        val charactersId: Long = 1009351

        val actual = remoteDataSource.getSeries(charactersId)

        Truth.assertThat(actual).hasSize(20)
        Truth.assertThat(actual[0].title).isEqualTo("5 Ronin (2011)")
    }
}