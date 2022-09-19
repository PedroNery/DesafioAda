package com.projeto.searchmovies

import android.accounts.NetworkErrorException
import androidx.lifecycle.Observer
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState
import com.projeto.searchmovies.data.mapper.toDataUi
import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCase
import com.projeto.searchmovies.presentation.state.SearchMovieState
import com.projeto.searchmovies.presentation.viewmodel.SearchMoviesViewModel
import com.projeto.test.unit.InstantLiveDataAndCoroutineRules
import com.projeto.test.unit.ViewModelTestRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.bytebuddy.utility.RandomString
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchMoviesViewModelTest {

    private val searchMoviesUseCase: SearchMoviesUseCase = mockk()
    private val initialState: SearchMovieState = SearchMovieState()
    private lateinit var viewModel: SearchMoviesViewModel
    private lateinit var stateObserver: Observer<UIState>
    private lateinit var actionObserver: Observer<UIAction>

    @get:Rule
    val viewModelRule = ViewModelTestRule(
        stateObserver = mockk(relaxed = true),
        actionObserver = mockk(relaxed = true)
    )

    @get:Rule
    val instantLiveDataAndCoroutineRules = InstantLiveDataAndCoroutineRules()

    @Before
    fun setUp(){
        viewModel = SearchMoviesViewModel(searchMoviesUseCase, TestCoroutineDispatcher())
        stateObserver = viewModelRule.getStateObsever()
        actionObserver = viewModelRule.getActionObsever()
    }

    @Test
    fun `searchMovieByName should set showContent when useCase return a SearchDomain with true response`() = runBlockingTest {
        //Given
        val anyString = RandomString().nextString()
        val movieMock = MovieDomain("poster", "title", "id")
        val searchDomainMock = SearchDomain(
            response = true,
            search = listOf(movieMock),
            totalResults = "0",
            error = null
        )
        val searchDataUIMock = searchDomainMock.toDataUi()
        every {
            searchMoviesUseCase.getMoviesByName(anyString)
        } returns flow { emit(searchDataUIMock) }

        //When
        viewModel.searchMovieByName(anyString)

        //Then
        verifySequence {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(initialState.showLoading(true))
            stateObserver.onChanged(initialState.showContent(searchDataUIMock.search))
        }
    }

    @Test
    fun `searchMovieByName should set showError when useCase return a SearchDomain with false response`() = runBlockingTest {
        //Given
        val anyString = RandomString().nextString()
        val movieMock = MovieDomain("poster", "title", "id")
        val searchDomainMock = SearchDomain(
            response = false,
            search = listOf(movieMock),
            totalResults = "0",
            error = "Nada encontrado"
        )
        val searchDataUIMock = searchDomainMock.toDataUi()
        every {
            searchMoviesUseCase.getMoviesByName(anyString)
        } returns flow { emit(searchDataUIMock) }

        //When
        viewModel.searchMovieByName(anyString)

        //Then
        verifySequence {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(initialState.showLoading(true))
            stateObserver.onChanged(initialState.showError(searchDataUIMock.error))
        }
    }

    @Test
    fun `searchMovieByName should set showError when useCase return IllegalArgumentException`() = runBlockingTest {
        //Given
        val anyString = RandomString().nextString()
        every {
            searchMoviesUseCase.getMoviesByName(anyString)
        } returns flow { throw IllegalArgumentException("Digite ao menos 3 letras") }

        //When
        viewModel.searchMovieByName(anyString)

        //Then
        verifySequence {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(initialState.showLoading(true))
            stateObserver.onChanged(initialState.showError("Digite ao menos 3 letras"))
        }
    }

    @Test
    fun `searchMovieByName should set showLoading false when useCase return other Exception`() = runBlockingTest {
        //Given
        val anyString = RandomString().nextString()
        every {
            searchMoviesUseCase.getMoviesByName(anyString)
        } returns flow { throw NetworkErrorException() }

        //When
        viewModel.searchMovieByName(anyString)

        //Then
        verifySequence {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(initialState.showLoading(true))
            stateObserver.onChanged(initialState.showLoading(false))
        }
    }
}