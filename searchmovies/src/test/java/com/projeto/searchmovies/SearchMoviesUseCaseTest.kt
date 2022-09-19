package com.projeto.searchmovies

import app.cash.turbine.test
import com.projeto.searchmovies.data.mapper.toDataUi
import com.projeto.searchmovies.data.repository.MoviesRepository
import com.projeto.searchmovies.domain.model.SearchDomain
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCase
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCaseImpl
import com.projeto.test.unit.InstantLiveDataAndCoroutineRules
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class SearchMoviesUseCaseTest {

    private val moviesRepository: MoviesRepository = mockk()

    private val searchMoviesUseCaseImpl : SearchMoviesUseCase by lazy {
        SearchMoviesUseCaseImpl(moviesRepository)
    }

    @get:Rule
    val instantLiveDataAndCoroutineRules = InstantLiveDataAndCoroutineRules()

    @Test
    fun `getMoviesByName should return SearchDomain when movie name length is bigger then 2 and repository return success`() = runBlockingTest {
        //Given
        val searchDomain = SearchDomain(
            response = true,
            error = "",
            search = listOf(),
            totalResults = "0"
        )
        coEvery {
            moviesRepository.getMoviesByName(any())
        } returns flow { emit(searchDomain) }

        //When
        val movieListFlow = searchMoviesUseCaseImpl.getMoviesByName("Teste")

        //Then
        movieListFlow.test {
            assertEquals(searchDomain.toDataUi(), expectItem())
            expectComplete()
        }
    }

    @Test
    fun `getMoviesByName should throw Error when movie name length is bigger then 2 and repository return error`() = runBlockingTest {
        val error = Exception()
        //Given
        coEvery {
            moviesRepository.getMoviesByName(any())
        } returns flow { throw error }

        //When
        val movieListFlow = searchMoviesUseCaseImpl.getMoviesByName("Teste")

        //Then
        movieListFlow.test {
            Assert.assertEquals(error, expectError())
        }
    }

    @Test
    fun `getMoviesByName should throw IllegalArgumentException when movie name length is less then 2`() = runBlockingTest {
        //When
        val movieListFlow = searchMoviesUseCaseImpl.getMoviesByName("Te")

        //Then
        movieListFlow.test {
            val error: Throwable = expectError()
            Assert.assertThat(error, IsInstanceOf(IllegalArgumentException::class.java))
        }
    }

}