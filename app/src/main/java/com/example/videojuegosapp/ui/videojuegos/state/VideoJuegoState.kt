package com.example.videojuegosapp.ui.videojuegos.state

import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego

data class VideoJuegoState(
    val estaCargando: Boolean = false,
    val mensajeError: String? = null,
    val videoJuegos:List<VideoJuego> = emptyList(),
    val detalleVideoJuego: DetalleVideoJuego? = null,
    )