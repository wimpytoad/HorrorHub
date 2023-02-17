package com.toadfrogson.horrorhub.data.di

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApi
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApiImpl
import org.koin.dsl.module

val dataModule = module {
    factory<WebClient> { WebClient() }
    factory<GetMoviesApi> { GetMoviesApiImpl(get())}
}