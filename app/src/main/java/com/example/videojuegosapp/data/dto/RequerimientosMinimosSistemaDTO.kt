package com.example.videojuegosapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequerimientosMinimosSistemaDTO(
    @SerialName("graphics")
    val graficos: String,
    @SerialName("memory")
    val memoria: String,
    @SerialName("os")
    val so: String,
    @SerialName("processor")
    val procesador: String,
    @SerialName("storage")
    val almacenamiento: String
)