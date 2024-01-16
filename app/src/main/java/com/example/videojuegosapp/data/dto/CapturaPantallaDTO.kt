package com.example.videojuegosapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CapturaPantallaDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val imagen: String
)