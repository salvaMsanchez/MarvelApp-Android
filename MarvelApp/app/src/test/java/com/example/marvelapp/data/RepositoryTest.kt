package com.example.marvelapp.data

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.mappers.LocalToUiMapper
import com.example.marvelapp.data.mappers.RemoteToLocalMapper
import com.example.marvelapp.data.mappers.RemoteToUiMapper
import com.example.marvelapp.domain.models.CharacterDetail
import com.example.marvelapp.fakes.FakeLocalDataSource
import com.example.marvelapp.fakes.FakeRemoteDataSource
import com.example.marvelapp.utils.generateLocalCharacters
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

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
        repository = Repository(
            remoteDataSource,
            localDataSource,
            remoteToLocalMapper,
            localToUiMapper,
            remoteToUiMapper
        )
    }

    @Test
    fun `WHEN getCharacter THEN success character for detail`() = runTest {
        val character: CharacterLocal =
            CharacterLocal(1009351, "Hulk", "Description", "Photo", true)
        localDataSource.insertCharacters(listOf(character))
        val localCharacter: CharacterLocal =
            localDataSource.getCharacter(characterId = character.id)
        val characterDetail: CharacterDetail = localToUiMapper.mapCharacterDetail(localCharacter)

        val actual = repository.getCharacter(character.id)

        Truth.assertThat(actual).isEqualTo(characterDetail)
    }

    @Test
    fun `WHEN getComics THEN success comics list`() = runTest {
        val characterId: Long = 1009351

        val actual = repository.getComics(characterId)

        Assert.assertEquals(true, actual.isNotEmpty())
        Truth.assertThat(actual).hasSize(10)
    }

    @Test
    fun `WHEN getSeries THEN success series list`() = runTest {
        val characterId: Long = 1009351

        val actual = repository.getSeries(characterId)

        Assert.assertEquals(true, actual.isNotEmpty())
        Truth.assertThat(actual).hasSize(10)
    }

    @Test
    fun `WHEN updateFavoriteStatus THEN toggle character favorite status`() = runTest {
        val character: CharacterLocal =
            CharacterLocal(1009351, "Hulk", "Description", "Photo", true)
        localDataSource.insertCharacters(listOf(character))
        val localCharacterBeforeUpdate: CharacterLocal =
            localDataSource.getCharacter(characterId = character.id)

        repository.updateLocalFavoriteStatus(character.id, false)

        val localCharacterAfterUpdate: CharacterLocal =
            localDataSource.getCharacter(characterId = character.id)

        Truth.assertThat(localCharacterAfterUpdate.favorite).isEqualTo(false)
        Truth.assertThat(localCharacterAfterUpdate.favorite)
            .isNotEqualTo(localCharacterBeforeUpdate.favorite)
    }

    @Test
    fun `WHEN deleteCharactersMinusFirstTwenty THEN success removing`() = runTest {
        val localCharacters = generateLocalCharacters()
        localDataSource.insertCharacters(localCharacters)

        repository.deleteCharactersMinusFirstTwenty()
        val localCharactersListAfterRemoving: List<CharacterLocal> = localDataSource.getCharacters()

        Truth.assertThat(localCharactersListAfterRemoving).hasSize(20)
    }
}