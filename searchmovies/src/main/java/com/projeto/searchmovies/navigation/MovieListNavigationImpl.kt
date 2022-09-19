package com.projeto.searchmovies.navigation

import android.content.Context
import android.content.Intent
import com.projeto.navigation.MovieListNavigation
import com.projeto.searchmovies.presentation.view.activity.MovieActivity

internal class MovieListNavigationImpl : MovieListNavigation {
    override fun navigateToMovieList(context: Context) {
        val intent = Intent(context, MovieActivity::class.java)
        context.startActivity(intent)
    }
}