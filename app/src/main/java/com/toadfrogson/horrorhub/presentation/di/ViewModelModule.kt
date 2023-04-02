package com.toadfrogson.horrorhub.presentation.di

import androidx.lifecycle.ViewModel
import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
}