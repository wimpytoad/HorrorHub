package com.toadfrogson.horrorhub.data.di

import android.content.Context
import androidx.room.Room
import com.toadfrogson.horrorhub.data.api.GetMoviesApiImpl
import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.localData.MovieDatabase
import com.toadfrogson.horrorhub.data.repo.movielist.MoviesRepoImpl
import com.toadfrogson.horrorhub.domain.api.GetMoviesApi
import com.toadfrogson.horrorhub.domain.repo.MoviesRepo
import com.toadfrogson.horrorhub.domain.usecase.MovieListUseCase
import com.toadfrogson.horrorhub.domain.usecase.MovieListUseCaseIml
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebclientModule {
    @Provides
    @Singleton
    fun providesWebClient(): WebClient = WebClient()
}

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabaseModule {
    @Provides
    @Singleton
    fun provideMoviesDB(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movies_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMoviesDao(db: MovieDatabase) = db.movieDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieApiModule {
    @Binds
    abstract fun bindsMovieApi(impl: GetMoviesApiImpl): GetMoviesApi

    @Binds
    abstract fun provideMoviesRepo(impl: MoviesRepoImpl) : MoviesRepo
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds abstract fun bindsMovieListUseCase(impl: MovieListUseCaseIml) : MovieListUseCase
}