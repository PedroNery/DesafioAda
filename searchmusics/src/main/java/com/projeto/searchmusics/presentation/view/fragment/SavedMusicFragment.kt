package com.projeto.searchmusics.presentation.view.fragment

import androidx.navigation.fragment.findNavController
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.common.koin.aware.scopemodule.KoinFragment
import com.projeto.searchmusics.R
import com.projeto.searchmusics.databinding.FragmentSavedMusicsBinding
import com.projeto.searchmusics.di.MoviesSavedModule
import com.projeto.searchmusics.presentation.viewmodel.SavedMusicsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedMusicFragment : KoinFragment(R.layout.fragment_saved_musics, MoviesSavedModule) {

    private val binding: FragmentSavedMusicsBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val viewModel: SavedMusicsViewModel by viewModel()

}