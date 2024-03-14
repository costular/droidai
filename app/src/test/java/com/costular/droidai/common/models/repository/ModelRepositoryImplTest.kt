package com.costular.droidai.common.models.repository

import com.costular.droidai.common.models.datasource.ModelLocalDataSource
import com.costular.droidai.common.models.datasource.ModelRemoteDataSource
import com.costular.droidai.common.models.fake.ModelLlama2
import com.costular.droidai.common.models.model.ModelResponseDto
import com.costular.droidai.data.ModelDtoLlama2
import com.costular.droidai.data.ModelDtoMistral
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ModelRepositoryImplTest {

    lateinit var sut: ModelRepository

    private val localDataSource: ModelLocalDataSource = mockk(relaxUnitFun = true)
    private val remoteDataSource: ModelRemoteDataSource = mockk(relaxUnitFun = true)

    @BeforeEach
    fun setUp() {
        sut = ModelRepositoryImpl(
            modelRemoteDataSource = remoteDataSource,
            modelLocalDataSource = localDataSource,
        )
    }

    @Test
    fun `Should return models from remote data source`() = runTest {
        givenModels(DefaultModels)

        sut.getModels()

        coVerify { remoteDataSource.getModels() }
    }

    @Test
    fun `Should call local data source when set default model`() = runTest {
        sut.setDefaultModel(ModelLlama2)

        coVerify(exactly = 1) {
            localDataSource.setDefaultModel(ModelLlama2.name)
        }
    }

    @Test
    fun `Should return default model when get default model given it's stored in local`() =
        runTest {
            val name = ModelLlama2.name

            givenModels(DefaultModels)
            givenDefaultModel(ModelLlama2.name)

            val result = sut.getDefaultModel()

            assertThat(result.name).isEqualTo(name)
        }

    @Test
    fun `Should return first available model when get model given no default model is stored locally`() =
        runTest {
            givenModels(DefaultModels)
            givenDefaultModel(null)

            val result = sut.getDefaultModel()

            assertThat(result.name).isEqualTo(DefaultModels.models.first().name)
        }

    private fun givenModels(response: ModelResponseDto) {
        coEvery { remoteDataSource.getModels() } returns response
    }

    private fun givenDefaultModel(model: String?) {
        coEvery { localDataSource.getDefaultModel() } returns model
    }

    private companion object {
        val DefaultModels = ModelResponseDto(
            models = listOf(
                ModelDtoLlama2,
                ModelDtoMistral,
            )
        )
    }
}
