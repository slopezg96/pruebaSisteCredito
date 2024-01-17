package com.example.videojuegosapp.data.repositorio

import androidx.lifecycle.LiveData
import com.example.videojuegosapp.data.basedatos.dao.VideoJuegoDAO
import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad
import kotlinx.coroutines.flow.Flow

class VideoJuegoRepositorioLocal (private val dao: VideoJuegoDAO){

    suspend fun insertarVideoJuego(videoJuegoEntidad: VideoJuegoEntidad) = dao.insertarVideoJuego(videoJuegoEntidad)

    val favoritos: LiveData<List<VideoJuegoEntidad>> = dao.obtenerVideoJuegosFavoritos()
}