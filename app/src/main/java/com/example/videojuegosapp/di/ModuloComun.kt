package com.example.videojuegosapp.di

import com.example.videojuegosapp.data.api.VideoJuegoApiServicio
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorioImpl
import com.example.videojuegosapp.dominio.repositorio.VideoJuegoRepositorio
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val BASE_URL = "www.freetogame.com"

fun moduloComun() = module {
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }



            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP call$message" )
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 20000L
                connectTimeoutMillis = 20000L
                socketTimeoutMillis = 20000L
            }
        }
    }

    single { VideoJuegoApiServicio(httpClient = get()) }

    single<VideoJuegoRepositorio> { VideoJuegoRepositorioImpl(videoJuegoApiServicio = get()) }

    single {
        VideoJuegoViewModel(videojuegoRepositorio = get())
    }
}