package com.toadfrogson.horrorhub.data.di

import com.toadfrogson.horrorhub.data.client.WebClient
import com.toadfrogson.horrorhub.data.repo.movielist.GetMoviesApiImpl
import com.toadfrogson.horrorhub.domain.repo.GetMoviesApi
import org.koin.dsl.module

val dataModule = module {
    factory<WebClient> { WebClient() }
    factory<GetMoviesApi> { GetMoviesApiImpl(get())}
}