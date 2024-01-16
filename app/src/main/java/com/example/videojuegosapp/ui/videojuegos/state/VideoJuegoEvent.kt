package com.example.videojuegosapp.ui.videojuegos.state

import com.example.videojuegosapp.dominio.modelo.VideoJuego

sealed interface VideoJuegoEvent {

    data class irADetalleVideoJuego(val videoJuego: VideoJuego) : VideoJuegoEvent
}