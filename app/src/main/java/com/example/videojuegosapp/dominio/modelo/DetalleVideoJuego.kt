package com.example.videojuegosapp.dominio.modelo

import com.example.videojuegosapp.data.dto.CapturaPantallaDTO
import com.example.videojuegosapp.data.dto.RequerimientosMinimosSistemaDTO
import kotlinx.serialization.SerialName

data class DetalleVideoJuego(
    val titulo: String,
    val miniatura: String,
    val descripcionCorta: String,
    val descripcion: String,
    val fechaLanzamiento: String,
    val genero: String,
    val desarrollador: String,
    val urlPerfilFreeToGame: String,
    val urlVideoJuego: String,
    val id: Int,
    val requerimientosMinimosSistema: RequerimientosMinimosSistema,
    val plataforma: String,
    val editor: String,
    val capturasPantalla: List<CapturaPantalla>,
    val estado: String,
)