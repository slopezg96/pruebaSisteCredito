package com.example.videojuegosapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetalleVideoJuegoDTO(
    @SerialName("title")
    val titulo: String,
    @SerialName("thumbnail")
    val miniatura: String,
    @SerialName("short_description")
    val descripcionCorta: String,
    @SerialName("description")
    val descripcion: String,
    @SerialName("release_date")
    val fechaLanzamiento: String,
    @SerialName("genre")
    val genero: String,
    @SerialName("developer")
    val desarrollador: String,
    @SerialName("freetogame_profile_url")
    val urlPerfilFreeToGame: String,
    @SerialName("game_url")
    val urlVideoJuego: String,
    @SerialName("id")
    val id: Int,
    @SerialName("minimum_system_requirements")
    val requerimientosMinimosSistema: RequerimientosMinimosSistemaDTO,
    @SerialName("platform")
    val plataforma: String,
    @SerialName("publisher")
    val editor: String,
    @SerialName("screenshots")
    val capturasPantalla: List<CapturaPantallaDTO>,
    @SerialName("status")
    val estado: String,
)