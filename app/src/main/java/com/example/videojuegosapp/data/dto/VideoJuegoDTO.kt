package com.example.videojuegosapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VideoJuegoDTO(
    @SerialName("title")
    val titulo: String,
    @SerialName("thumbnail")
    val miniatura: String,
    @SerialName("short_description")
    val descripcionCorta: String,
    @SerialName("developer")
    val desarrollador: String,
    @SerialName("freetogame_profile_url")
    val urlPerfilFreeToGame: String,
    @SerialName("game_url")
    val urlVideojuego: String,
    @SerialName("genre")
    val genero: String,
    @SerialName("id")
    val id: Int,
    @SerialName("platform")
    val plataforma: String,
    @SerialName("publisher")
    val editor: String,
    @SerialName("release_date")
    val fechaLanzamiento: String
)