package com.example.marvelapp.data.local

import com.example.marvelapp.data.local.database.CharacterDAO
import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.utils.generateLocalCharacters
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    // SUT
    private lateinit var localDataSource: LocalDataSource

    // Dependencies
    private val dao: CharacterDAO = mockk()

    @Before
    fun setUp() {
        localDataSource = LocalDataSource(dao)
    }

    @Test
    fun `WHEN getCharacters THEN success characters list`() {
        val expected = generateLocalCharacters()
        every { dao.getCharacters() } returns expected

        val actual = localDataSource.getCharacters()

        Assert.assertEquals(true, actual.isNotEmpty())
        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `WHEN getCharacter THEN success character`() {
        val expected = CharacterLocal(1009351, "Hulk", "Description", "Photo", true)
        every { dao.getCharacter(expected.id) } returns expected

        val characterId: Long = 1009351
        val actual = localDataSource.getCharacter(characterId)

        Assert.assertEquals(expected.id, actual.id)
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.photo).isEqualTo(expected.photo)
        Truth.assertThat(actual.description).isEqualTo(expected.description)
        Truth.assertThat(actual.favorite).isEqualTo(expected.favorite)
    }

    @After
    fun tearDown() {

    }
}