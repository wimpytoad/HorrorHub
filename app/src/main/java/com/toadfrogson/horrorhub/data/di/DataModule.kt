package com.toadfrogson.horrorhub.data.di

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApiImpl
import com.toadfrogson.horrorhub.domain.repo.GetMoviesApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebclientModule {
    @Provides
    @Singleton
    fun providesWebClient() : WebClient = WebClient()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieApiModule {
    @Binds
    abstract fun bindsMovieApi(impl: GetMoviesApiImpl) : GetMoviesApi
}