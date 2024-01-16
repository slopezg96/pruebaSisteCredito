package com.example.videojuegosapp.data.api

import com.example.videojuegosapp.data.dto.VideoJuegoDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json


class VideoJuegoApiServicio(
    private val httpClient: HttpClient
) {

    suspend fun obtenerTodosLosVideoJuegos(): List<VideoJuegoDTO>{
        return httpClient.get("/api/games").bodyAsText().let { json->
            Json.decodeFromString(json)
        }
    }

    suspend fun obtenerDetalleVideoJuego(idVideoJuego: Int): HttpResponse {
        return httpClient.get("/api/game") {
            url {
                parameters.append("id", idVideoJuego.toString())
            }
        }
    }


}