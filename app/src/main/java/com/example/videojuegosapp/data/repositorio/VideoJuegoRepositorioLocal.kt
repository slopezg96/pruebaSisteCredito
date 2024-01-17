package com.example.videojuegosapp.data.repositorio

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.videojuegosapp.data.basedatos.VideoJuegosDataBase
import com.example.videojuegosapp.data.basedatos.dao.VideoJuegoDAO
import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad
import com.example.videojuegosapp.dominio.modelo.VideoJuego
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class VideoJuegoRepositorioLocal (private val dao: VideoJuegoDAO){

    suspend fun insertarVideoJuego(videoJuegoEntidad: VideoJuegoEntidad) = dao.insertarVideoJuego(videoJuegoEntidad)
    fun obetnerVideoJuegosFavoritos() = dao.obtenerVideoJuegosFavoritos()
}