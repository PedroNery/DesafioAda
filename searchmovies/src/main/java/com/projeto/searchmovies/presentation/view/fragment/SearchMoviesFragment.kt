package com.projeto.searchmovies.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.projeto.common.base.extensions.onAction
import com.projeto.common.base.extensions.onStateChange
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.common.koin.aware.scopemodule.KoinFragment
import com.projeto.designsystem.recyclerview.GridSpacingItemDecoration
import com.projeto.searchmovies.R
import com.projeto.searchmovies.databinding.FragmentSearchMoviesBinding
import com.projeto.searchmovies.di.MovieSearchModule
import com.projeto.searchmovies.presentation.adapter.MovieSearchAdapter
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI
import com.projeto.searchmovies.presentation.intent.SearchMoviesAction
import com.projeto.searchmovies.presentation.state.SearchMovieState
import com.projeto.searchmovies.presentation.viewmodel.SearchMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment : KoinFragment(R.layout.fragment_search_movies, MovieSearchModule) {

    private val binding: FragmentSearchMoviesBinding by viewBinding()
    private val viewModel: SearchMoviesViewModel by viewModel()
    private val adapter: MovieSearchAdapter by lazy {
        MovieSearchAdapter(::onMovieItemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initEditTextObserver()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
    }

    private fun initRecyclerView() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.addItemDecoration(GridSpacingItemDecoration(2, 70, false))
    }

    private fun initEditTextObserver() {
        binding.etSearchMovie.onTextChanged = {
            viewModel.searchMovieByName(it)
        }
    }

    private fun handleState(state: SearchMovieState) {
        binding.loading.isVisible = state.showLoading
        binding.tvError.isVisible = state.showError
        binding.tvError.text = state.errorMessage
        binding.rvMovieList.isVisible = state.showContent
        adapter.submitList(state.movieList)
    }

    private fun handleAction(action: SearchMoviesAction) {
        when (action) {
            SearchMoviesAction.ReturnToSavedMovies -> {
                findNavController().popBackStack()
            }
            is SearchMoviesAction.SaveMovieLocal -> {
                viewModel.saveMovie(movieItemDataUI = action.movie)
            }
        }
    }

    private fun onMovieItemClick(movieItemDataUI: MovieItemDataUI) {
        viewModel.setSaveMovieAction(movieItemDataUI)
    }
}