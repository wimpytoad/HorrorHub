package com.toadfrogson.horrorhub.presentation.di

import com.toadfrogson.horrorhub.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}