package com.toadfrogson.horrorhub.data.client

import com.toadfrogson.horrorhub.domain.api.apiResponse.ApiResponse
import com.toadfrogson.horrorhub.domain.api.apiResponse.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import java.lang.Exception
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

const val baseUrl = "https://api.themoviedb.org/"
const val apiKey = "?api_key=8c241427387d1477f9214911ad446bbb"
class WebClient {

    suspend inline fun <reified T: Any>makeClientGet(endpoint: String) : ApiResponse<T> {
        val url = baseUrl + endpoint + apiKey
        return try {
            val client = getWebClient()
            val response = client.get(url).body<T>()
            ApiResponse(true, response, null)
        } catch (e: Exception) {
            ApiResponse(false, null, ErrorResponse(e.message.orEmpty(), 0))
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getWebClient() = HttpClient(Android){
        engine {
            connectTimeout = 100_000
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }
    }
}