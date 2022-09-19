package com.projeto.searchmovies.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.common.koin.aware.scopemodule.KoinFragment
import com.projeto.designsystem.recyclerview.GridSpacingItemDecoration
import com.projeto.searchmovies.R
import com.projeto.searchmovies.databinding.FragmentSavedMoviesBinding
import com.projeto.searchmovies.presentation.adapter.MovieSavedAdapter
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI
import androidx.navigation.fragment.findNavController
import com.projeto.common.base.extensions.onAction
import com.projeto.common.base.extensions.onStateChange
import com.projeto.searchmovies.di.MoviesSavedModule
import com.projeto.searchmovies.presentation.intent.SavedMoviesAction
import com.projeto.searchmovies.presentation.state.SavedMovieState
import com.projeto.searchmovies.presentation.viewmodel.SavedMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedMoviesFragment : KoinFragment(R.layout.fragment_saved_movies, MoviesSavedModule) {

    private val binding: FragmentSavedMoviesBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val viewModel: SavedMoviesViewModel by viewModel()
    private val adapter: MovieSavedAdapter by lazy {
        MovieSavedAdapter(
            ::navigateToMovieSearch,
            ::deleteMovie
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getAllMovies()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
    }

    private fun handleState(state: SavedMovieState) {
        binding.loading.isVisible = state.showLoading
        adapter.submitList(state.movieList?.addSearch())
    }

    private fun navigateToMovieSearch() {
        viewModel.setNavigateToSearchAction()
    }

    private fun deleteMovie(movieId: String) {
        viewModel.deleteMovie(movieId)
    }

    private fun handleAction(savedMoviesAction: SavedMoviesAction) {
        when(savedMoviesAction){
            SavedMoviesAction.NavigateToSearch -> {
                navController.navigate(SavedMoviesFragmentDirections.actionSavedMoviesFragmentToSearchMoviesFragment())
            }
            SavedMoviesAction.ReloadList ->{
                viewModel.getAllMovies()
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.addItemDecoration(GridSpacingItemDecoration(2, 70, false))
    }

    private fun List<MovieItemDataUI>.addSearch() : List<MovieItemDataUI>{
        val list = mutableListOf(MovieItemDataUI(type = 0))
        list.addAll(this)
        return list
    }
}